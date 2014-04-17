/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 * 
 */
package jlib.misc;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: DataFileLineReader.java,v 1.24 2007/10/25 15:13:11 u930di Exp $
 */
public class DataFileLineReader extends BaseDataFileBuffered
{
	private BufferedInputStream m_in = null;
	
	private LineRead m_lineRead = new LineRead();

	private byte[] m_tReadBytesAHead = null;
	private int m_nLastPositionInReadAHead = 0;
	private int m_nFirstPositionInReadAHead = 0;
	private int m_nNbByteReadAHead = 100; 	// this size nmust be >= size of the largest record readable
	private int m_nReservedHeaderSpace = 0;
		
	public DataFileLineReader(String csName, int nBufferChunkReadAHead, int nReservedHeaderSpace)
	{
		m_csName = csName;
		m_nNbByteReadAHead = nBufferChunkReadAHead;
		m_nReservedHeaderSpace = nReservedHeaderSpace;
		m_tReadBytesAHead = new byte[(m_nNbByteReadAHead+m_nReservedHeaderSpace) * 2];		
	}
	
	private boolean doOpen()
	{
		try
		{
			if(!StringUtil.isEmpty(getName()))
			{
				m_in = new BufferedInputStream(new DataInputStream(new FileInputStream(getName())));
				m_nLastPositionInReadAHead = m_nReservedHeaderSpace;
				m_nFirstPositionInReadAHead = m_nReservedHeaderSpace;
				return true;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	public boolean open()
	{
		return open(null);
	}
	
	public boolean open(LogicalFileDescriptor logicalFileDescriptor)
	{
		if(logicalFileDescriptor != null && logicalFileDescriptor.isDummyFile())
			return false;
		
		boolean bOpened = doOpen();
		if(bOpened && logicalFileDescriptor != null)
		{
			logicalFileDescriptor.readFileHeader(this);
		}
		return bOpened; 
	}

	public boolean flush()
	{
		return true;
	}
	
	public boolean close()
	{
		try
		{
			if(m_in != null)
			{
				m_in.close();
				m_in = null;
				m_nFirstPositionInReadAHead = m_nReservedHeaderSpace;
				m_nLastPositionInReadAHead = m_nReservedHeaderSpace;
				return true;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isOpen()
	{
		if(m_in != null)
			return true;
		return false;
	}
	
	private int getNextLFPosition()
	{
		int n = m_nFirstPositionInReadAHead;
		while(n < m_nLastPositionInReadAHead)
		{
			if(m_tReadBytesAHead[n] == FileEndOfLine.LF)
				return n;
			n++;
		}
		return -1;
	}
	
	private boolean isPositionAtOffsetInReadAHead(int nOffset)
	{
		if(m_nFirstPositionInReadAHead + nOffset <= m_nLastPositionInReadAHead)
			return true;
		return false;
	}
	
	public LineRead readVariableLengthLine(boolean bTryReadNextLF, boolean bHeaderIsInt, LineRead lineOut)	// Read a vairable length line (length is given in record header 4 bytes)
	{
		LineRead recordHeader = readBuffer(4, false);
		if(recordHeader != null)
		{
			int nLength = 0;
			if(bHeaderIsInt)
				nLength = recordHeader.getAsLittleEndingUnsignBinaryInt();
			else
			{
				// VH Mode
				//nLength = recordHeader.getAsLittleEndingUnsignBinaryShort();	// The header is 2 bytes long, with the next 2 bytes at 0
				nLength = recordHeader.readAndConvertHeaderVHToVBMode();	// The header is converted in the buffer as a VB header 
			}
			
			if(lineOut == null)
				lineOut = new LineRead();
			lineOut.resetAndGaranteeBufferStorage(4 + nLength, m_nNbByteReadAHead + 4 + nLength);
			lineOut.append(recordHeader);
	
			LineRead recordBody = readBuffer(nLength, bTryReadNextLF);
			lineOut.append(recordBody);
			
			return lineOut;
		}
		return null;
	}
	
	public LineRead readDirect(int nLength)
	{
		try
		{
			int nNBytesRead = m_in.read(m_tReadBytesAHead, 0, nLength);
			if(nNBytesRead != -1)
			{
				//m_nFirstPositionInReadAHead += nLength;
				m_lineRead.set(m_tReadBytesAHead, 0, nLength, 0);
			}
			return m_lineRead;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	
	public LineRead readBuffer(int nLength, boolean bTryReadNextLF)
	{
		if(m_in == null)
			return null;
		int nFullLength = nLength;
		if(bTryReadNextLF)
			nFullLength++;
		if(isPositionAtOffsetInReadAHead(nFullLength))	// The next recoprd, including optional LF is already read the in read ahead buffer  
		{
			m_lineRead.set(m_tReadBytesAHead, m_nFirstPositionInReadAHead, nFullLength, m_nReservedHeaderSpace);
			m_nFirstPositionInReadAHead += nFullLength;
			if(bTryReadNextLF)
			{
				// check trailing LF
				if(!m_lineRead.manageTrailingLF())	// No traling LF: Read 1 byte too far
				{
					m_nFirstPositionInReadAHead--;
				}					
			}
			return m_lineRead;
		}
		else	// No full data in the read ahead buffer
		{
			return readAhead(nFullLength, bTryReadNextLF);
		}
	}
	
	private LineRead readAhead(int nFullLength, boolean bTryReadNextLF)
	{
		int nLengthSource = m_nLastPositionInReadAHead - m_nFirstPositionInReadAHead;
	
		// Keep the data already read
		for(int n=0; n<nLengthSource; n++)
			m_tReadBytesAHead[m_nReservedHeaderSpace+n] = m_tReadBytesAHead[n+m_nFirstPositionInReadAHead]; 
		m_nFirstPositionInReadAHead = m_nReservedHeaderSpace;
		m_nLastPositionInReadAHead = m_nReservedHeaderSpace+nLengthSource;	
		
		// Read next data chunk
		try
		{	
			int nNBytesRead = m_in.read(m_tReadBytesAHead, m_nLastPositionInReadAHead, m_nNbByteReadAHead);
			if(nNBytesRead != -1)
			{
				m_nLastPositionInReadAHead += nNBytesRead;
				// Read some data from readAhead buffer
				if(isPositionAtOffsetInReadAHead(nFullLength))	// The next recoprd, including optional LF is already read the in read ahead buffer  
				{
					m_lineRead.set(m_tReadBytesAHead, m_nFirstPositionInReadAHead, nFullLength, m_nReservedHeaderSpace);
					m_nFirstPositionInReadAHead += nFullLength;
					if(bTryReadNextLF)
					{
						// check trailing LF
						if(!m_lineRead.manageTrailingLF())	// No traling LF: Read 1 byte too far
						{
							m_nFirstPositionInReadAHead--;
						}
					}
					return m_lineRead;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
		setEOF(true);
		return null;
	}

	public LineRead readNextUnixLine()
	{
		if(m_in == null)
			return null;
		
		int nPositionNextLF = getNextLFPosition();
		if(nPositionNextLF != -1)	// Found position of the next LF
		{
			int nLength = nPositionNextLF - m_nFirstPositionInReadAHead;
			m_lineRead.set(m_tReadBytesAHead, m_nFirstPositionInReadAHead, nLength, m_nReservedHeaderSpace);
			m_nFirstPositionInReadAHead = nPositionNextLF+1; 
			return m_lineRead;
		}
		else	// Not found the position of the next lf
		{
			int nLengthSource = m_nLastPositionInReadAHead - m_nFirstPositionInReadAHead;

			// Keep the data already read
			for(int n=0; n<nLengthSource; n++)
				m_tReadBytesAHead[m_nReservedHeaderSpace+n] = m_tReadBytesAHead[n+m_nFirstPositionInReadAHead]; 
			m_nFirstPositionInReadAHead = m_nReservedHeaderSpace;
			m_nLastPositionInReadAHead = m_nReservedHeaderSpace+nLengthSource;

			// Read next data chunk
			try
			{	
				int nNBytesRead = m_in.read(m_tReadBytesAHead, m_nLastPositionInReadAHead, m_nNbByteReadAHead);
				if(nNBytesRead != -1)
				{
					m_nLastPositionInReadAHead += nNBytesRead;
					// Read some data from readAhead buffer
					nPositionNextLF = getNextLFPosition();
					if(nPositionNextLF != -1)	// Found position of the next LF
					{
						int nBodyLength = nPositionNextLF - m_nFirstPositionInReadAHead;
						m_lineRead.set(m_tReadBytesAHead, m_nFirstPositionInReadAHead, nBodyLength, m_nReservedHeaderSpace);
						m_nFirstPositionInReadAHead = nPositionNextLF+1;
						return m_lineRead;
					}	
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}			
		}
		setEOF(true);
		return null;
	}
	
	public void writeRecord(String cs)
	{
	}
	
	public void writeEndOfRecordMarker()
	{
	}
	
	public void writeWithEOL(byte[] tBytes, int nSize)
	{
	}
	
	public void writeWithEOL(LineRead lineRead)
	{
	}

	public void write(byte[] tBytes)
	{
	}
	
	public void write(byte[] tBytes, int nOffset, int nLength)
	{
	}
	
	public boolean readEndOfLineMarker()
	{
		return false;
	}
	
	public byte[] read(int nSize)
	{
		try
		{	
			int nNBytesRead = m_in.read(m_tReadBytesAHead, 0, nSize);
			if(nNBytesRead != -1)
			{
				return m_tReadBytesAHead;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String toString()
	{
		String cs = m_csName + " (";
		if(isOpen())
			cs += "Opened";
		else
			cs += "Closed";
		cs += ")";
		return cs;
	}
	
	public void rewrite(byte[] tBytes, int nOffset, int nLength)
	{
	}
	
	public void rewriteWithEOL(byte[] tbyDest, int nSize)
	{
	}

	public boolean isReadable()
	{
		return true;
	}
	
	public boolean isWritable()
	{
		return false;
	}
	
	public boolean isUpdateable()
	{
		return false;
	}
	
	public long getFileCurrentPosition()
	{
		return -1;
	}
	
	public boolean setFileCurrentPosition(long lCurrentPosition)
	{
		return false;
	}
	
	public boolean savePosition(int nMaxReadAheadSize)
	{
		if(m_in != null && m_in.markSupported())
		{
			m_in.mark(nMaxReadAheadSize);
			return true;
		}
		return false;
	}

	public boolean returnAtSavedPosition()
	{
		m_nFirstPositionInReadAHead = 0;
		m_nLastPositionInReadAHead = 0;
		if(m_in != null && m_in.markSupported())
		{
			try
			{
				m_in.reset();
				return true;
			}
			catch (IOException e)
			{
				int n = 0;
			}
		}
		return false;		
	}
	
}
