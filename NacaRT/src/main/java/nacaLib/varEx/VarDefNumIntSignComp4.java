/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.varEx;

import java.math.BigDecimal;

import jlib.misc.*;
import nacaLib.bdb.BtreeSegmentKeyTypeFactory;
import nacaLib.mathSupport.MathAdd;
import nacaLib.misc.StringAsciiEbcdicUtil;
import nacaLib.sqlSupport.CSQLItemType;
import nacaLib.tempCache.CStr;
import nacaLib.tempCache.CStrNumber;
import nacaLib.tempCache.TempCacheLocator;

/**
 * @author U930DI
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VarDefNumIntSignComp4 extends VarDefNum
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VarDefNumIntSignComp4(VarDefBase varDefParent, DeclareType9 declareType9, NumericValue numericValue)
	{
		super(varDefParent, declareType9.m_varLevel);
		m_nNbDigitInteger = numericValue.m_nNbDigitInteger;
	}
	
	protected VarDefNumIntSignComp4()
	{
		super();
	}
	
//	VarDefNumIntSignComp4(VarDefNumIntSignComp4 varDefSource)
//	{
//		super(varDefSource);
//		m_nNbDigitInteger = varDefSource.m_nNbDigitInteger;
//	}
//	
//	VarDefBuffer deepDuplicate()
//	{
//		return new VarDefNumIntSignComp4(this);
//	}
	
	void transfer(VarBufferPos bufferSource, VarAndEdit Dest)
	{
		Dest.m_varDef.write(Dest.m_bufferPos, this, bufferSource);
	}
	
	CSQLItemType getSQLType()
	{
		return getIntegerSQLType(m_nNbDigitInteger);
	}
	
	protected VarDefBuffer allocCopy()
	{
		VarDefNumIntSignComp4 v = new VarDefNumIntSignComp4();
		v.m_nNbDigitInteger = m_nNbDigitInteger;
		return v;
	}
	
	public int getBodyLength()
	{
		return m_nTotalSize;
	}
	
	protected int getHeaderLength()
	{
		return 0;
	}

	
	public int getSingleItemRequiredStorageSize()
	{
		return getSingleItemRequiredStorageSizeForComp4(m_nNbDigitInteger);
	}

	void write(VarBufferPos buffer, char c)
	{
		int n = NumberParser.getAsUnsignedInt(c);
		writeIntSignComp4(buffer, n);
	}
	
	public void write(VarBufferPos buffer, String cs)
	{
		long l = NumberParser.getAsLong(cs);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	public void inc(VarBufferPos buffer, int n)
	{
		// PJD: Optimization
//		if(!buffer.isLastChecksumValid(m_nTotalSize))
//			buffer.m_nLastValue = getAsDecodedInt(buffer);
//		buffer.m_nLastValue += n;
//		write(buffer, buffer.m_nLastValue);			
		int nVal = getAsDecodedInt(buffer);
		nVal += n;
		write(buffer, nVal);
	}
	
	
	public void inc(VarBufferPos buffer, BigDecimal bdStep)
	{
		CStr s1 = getDottedSignedString(buffer);
		Dec dec = MathAdd.inc(s1, bdStep);
		write(buffer, dec);
	}
	
	public void write(VarBufferPos buffer, int n)
	{
		writeIntSignComp4(buffer, n);
	}
	
	public void write(VarBufferPos buffer, long l)
	{
		writeIntSignComp4(buffer, (int)l);
	}
	
	void write(VarBufferPos buffer, double d)
	{
		int n = (int) d;
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, Dec dec)
	{
		writeIntSignComp4(buffer, dec.getSignedInt());
	}
	
	public void write(VarBufferPos buffer, BigDecimal bigDecimal)
	{
		long lValue = bigDecimal.longValue();
		writeIntSignComp4AsLong(buffer, lValue);
	}
	
	void write(VarBufferPos buffer, VarDefG varSource, VarBufferPos bufferSource)
	{
		// http://h71000.www7.hp.com/DOC/73final/6297/6297_profile_010.html#alpha_elem_move_sec
		// If the sending item is a group item, and the receiving item is an elementary item, the compiler ignores the receiving item description except for the size description, in bytes, and any JUSTIFIED clause. It conducts no conversion or editing on the sending item's data
		internalPhysicalWrite(buffer, varSource, bufferSource); 
	}

	void write(VarBufferPos buffer, VarDefX varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getUnsignedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefFPacAlphaNum varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getUnsignedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefFPacRaw varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getUnsignedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecComp3 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}

	void write(VarBufferPos buffer, VarDefNumDecComp4 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecSignComp4 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}

	
	void write(VarBufferPos buffer, VarDefNumDecSignComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecSignComp3 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecSignLeadingComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumDecSignTrailingComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntComp0Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntComp3 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntComp3Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}

	
	void write(VarBufferPos buffer, VarDefNumIntComp4 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntComp4Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}

	void write(VarBufferPos buffer, VarDefNumIntSignComp0Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, 4);
	}

	void write(VarBufferPos buffer, VarDefNumIntSignComp3 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefFPacNumIntSignComp3 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignComp3Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}

	public void moveIntoSameType(VarBufferPos buffer, VarDefBuffer varSource, VarBufferPos bufferSource)
	{
		if(m_nTotalSize == varSource.m_nTotalSize)	// Same type and same size: Directly copy bytes
		{
			int nPositionDest = buffer.m_nAbsolutePosition;
			int nPositionSource = bufferSource.m_nAbsolutePosition;
			for(int n=0; n<m_nTotalSize; n++)
			{
				buffer.m_acBuffer[nPositionDest++] = bufferSource.m_acBuffer[nPositionSource++];
			}
			return ;
		}
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignComp4 varSource, VarBufferPos bufferSource)
	{
		if(m_nTotalSize == varSource.m_nTotalSize)	// Same type and same size: Directly copy bytes
		{
			int nPositionDest = buffer.m_nAbsolutePosition;
			int nPositionSource = bufferSource.m_nAbsolutePosition;
			for(int n=0; n<m_nTotalSize; n++)
			{
				buffer.m_acBuffer[nPositionDest++] = bufferSource.m_acBuffer[nPositionSource++];
			}			
			return ;
		}
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	
	void write(VarBufferPos buffer, VarDefNumIntSignComp4Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignLeadingComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignLeadingComp0Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignTrailingComp0 varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefNumIntSignTrailingComp0Long varSource, VarBufferPos bufferSource)
	{
		long l = varSource.getAsDecodedLong(bufferSource);
		writeIntSignComp4AsLong(buffer, l);
	}
	
	void write(VarBufferPos buffer, VarDefEditInMap varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefEditInMapRedefine varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}	
	
	void write(VarBufferPos buffer, VarDefEditInMapRedefineNumEdited varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}
	
	void write(VarBufferPos buffer, VarDefEditInMapRedefineNum varSource, VarBufferPos bufferSource)
	{
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}

	
	void write(VarBufferPos buffer, VarDefNumEdited varSource, VarBufferPos bufferSource)
	{
		// see http://www.helsinki.fi/atk/unix/dec_manuals/cobv27ua/cobrm_029.htm#index_x_737
		int n = varSource.getAsDecodedInt(bufferSource);
		writeIntSignComp4(buffer, n);
	}

	
	
	
	void write(VarBufferPos buffer, CobolConstantZero cst)
	{
		writeIntSignComp4(buffer, 0);
	}

	void write(VarBufferPos buffer, CobolConstantSpace cst)
	{
		// Do nothing into numeric vars
	}

	void write(VarBufferPos buffer, CobolConstantLowValue cst)
	{
		// Do nothing into numeric vars
	}

	void write(VarBufferPos buffer, CobolConstantHighValue cst)
	{
		// Do nothing into numeric vars
	}
	
	void write(VarBufferPos buffer, CobolConstantZero cst, int nOffsetPosition, int nNbChar)
	{
	}

	void write(VarBufferPos buffer, CobolConstantSpace cst, int nOffsetPosition, int nNbChar)
	{
	}
	
	void write(VarBufferPos buffer, CobolConstantLowValue cst, int nOffsetPosition, int nNbChar)
	{
	}
	
	void write(VarBufferPos buffer, CobolConstantHighValue cst, int nOffsetPosition, int nNbChar)
	{
	}

	void write(VarBufferPos buffer, String csValue, int nOffsetPosition, int nNbChar)
	{		
	}
	
//	public void initialize(VarBufferPos buffer)
//	{
//		writeIntSignComp4(buffer, 0);
//	}

	public void initializeAtOffset(VarBufferPos buffer, int nOffset, InitializeCache initializeCache)
	{
		writeIntSignComp4(buffer, nOffset, 0);
		if(initializeCache != null)
			initializeCache.addItem(buffer, nOffset, getSingleItemRequiredStorageSize());
	}

//	void initialize(VarBufferPos buffer, String cs)
//	{
//	}

	void initializeAtOffset(VarBufferPos buffer, int nOffset, String cs)
	{		
	}

//	void initialize(VarBufferPos buffer, int n)
//	{
//		writeIntSignComp4(buffer, n);
//	}
	
	void initializeAtOffset(VarBufferPos buffer, int nOffset, int n)
	{
		writeIntSignComp4(buffer, nOffset, n);
	}
	
	void initializeEdited(VarBufferPos buffer, String cs)
	{
	}
	
	void initializeEdited(VarBufferPos buffer, int n)
	{
	}
	
	void initializeEditedAtOffset(VarBufferPos buffer, int nOffset, int nValue)
	{
	}

	void initializeEditedAtOffset(VarBufferPos buffer, int nOffset, double dValue)
	{
	}

	private void writeIntSignComp4(VarBufferPos buffer, int n)
	{
		int nBinaryNumberStorage = getSingleItemRequiredStorageSize();
		if(nBinaryNumberStorage == 4)	// int
		{
			buffer.setIntAt(buffer.m_nAbsolutePosition, n);
			return ;
		}		
		else if(nBinaryNumberStorage == 2)	// short
		{
			short s = (short)n;
			// PJD Optimization
			//int nChecksum = buffer.setShortAt(buffer.m_nAbsolutePosition, s);
			//buffer.setChecksum(nChecksum);	// PJD Optimization
			buffer.setShortAt(buffer.m_nAbsolutePosition, s);
			return ;
		}
		else		
		{
			long l = n;
			buffer.setLongAt(buffer.m_nAbsolutePosition, l);
			return ;
		}
	}
	
	private int writeIntSignComp4(VarBufferPos buffer, int nOffset, int n)
	{
		int nPos = buffer.m_nAbsolutePosition+nOffset;
		int nBinaryNumberStorage = getSingleItemRequiredStorageSize();
		if(nBinaryNumberStorage == 4)	// int
		{
			buffer.setIntAt(nPos, n);
			return nPos + 4;
		}		
		else if(nBinaryNumberStorage == 2)	// short
		{
			short s = (short)n;
			buffer.setShortAt(nPos, s);
			return nPos + 2;
		}
		else			// long
		{
			long l = n;
			buffer.setLongAt(nPos, l);
			return nPos + 8;
		}
	}

	
	private void writeIntSignComp4AsLong(VarBufferPos buffer, long l)
	{
		int nBinaryNumberStorage = getSingleItemRequiredStorageSize();
		if(nBinaryNumberStorage == 4)	// short
		{
			buffer.setIntAt(buffer.m_nAbsolutePosition, (int)l);
			return ;
		}		
		else if(nBinaryNumberStorage == 2)	// long
		{
			short s = (short)l;
			buffer.setShortAt(buffer.m_nAbsolutePosition, s);
			return ;
		}
		else		
		{
			buffer.setLongAt(buffer.m_nAbsolutePosition, l);
			return ;
		}
	}

//	GenericValue getGenericValue(VarBuffer buffer)
//	{
//		int n = internalReadIntSignComp4(buffer);
//		GenericValueInt gv = new GenericValueInt(n);
//		return gv;
//	}		
	
	int getAsDecodedInt(VarBufferPos buffer)
	{
		return internalReadIntSignComp4(buffer);
	}

	int getAsDecodedUnsignedInt(VarBufferPos buffer)
	{
		int n = internalReadIntSignComp4(buffer);
		if(n < 0)
			return -n;
		return n;
	}

	long getAsDecodedLong(VarBufferPos buffer)
	{
		long l = internalReadIntSignComp4AsLong(buffer);
		return l;
	}
		
	Dec getAsDecodedDec(VarBufferPos buffer)
	{
		long lInt = internalReadIntSignComp4AsLong(buffer);
		Dec dec = new Dec(lInt, "");
		return dec;
	}
	
	CStr getAsAlphaNumString(VarBufferPos buffer)
	{		
		int nValue = internalReadIntSignComp4(buffer);
		if(nValue < 0)
			nValue = -nValue;
		CStrNumber csNum = TempCacheLocator.getTLSTempCache().getCStrNumber();
		csNum.getAsAbsoluteIntComp0String(nValue, m_nNbDigitInteger);
		
		//String cs = RWNumIntComp0.getAsAbsoluteIntComp0String(nValue, m_nNbDigitInteger);
		return csNum;
	}	
	
	CStr getDottedSignedString(VarBufferPos buffer)
	{	
		int nValue = internalReadIntSignComp4(buffer);
		CStrNumber csNum = TempCacheLocator.getTLSTempCache().getCStrNumber(); 
		csNum.valueOf(nValue);
		return csNum;
	}
	
	CStr getDottedSignedStringAsSQLCol(VarBufferPos buffer)
	{	
		int nValue = internalReadIntSignComp4WithMaxDigits(this, buffer, m_nNbDigitInteger);
		CStrNumber csNum = TempCacheLocator.getTLSTempCache().getCStrNumber(); 
		csNum.valueOf(nValue);
		return csNum;
	}

	int compare(ComparisonMode mode, VarBufferPos bufferSource, VarAndEdit var2)
	{
		return var2.m_varDef.compare(mode, var2.m_bufferPos, this, bufferSource);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecComp0 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecComp3 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}

	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecComp4 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecSignComp4 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecSignComp0 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecSignComp3 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecSignLeadingComp0 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumDecSignTrailingComp0 varDefNum1, VarBufferPos buffer1)
	{
		Dec dec1 = varDefNum1.getAsDecodedDec(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(dec1, n2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp0 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp0Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp3 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp3Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}

	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp4 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntComp4Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp0 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp0Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp3 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefFPacNumIntSignComp3 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp3Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp4 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}	
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignComp4Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedLong(buffer1);
		long l2 = getAsDecodedLong(buffer2);
		return internalCompare(l1, l2);
	}

	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignLeadingComp0 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}	
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignLeadingComp0Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedInt(buffer1);
		long l2 = getAsDecodedInt(buffer2);
		return internalCompare(l1, l2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignTrailingComp0 varDefNum1, VarBufferPos buffer1)
	{
		int n1 = varDefNum1.getAsDecodedInt(buffer1);
		int n2 = getAsDecodedInt(buffer2);
		return internalCompare(n1, n2);
	}	
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumIntSignTrailingComp0Long varDefNum1, VarBufferPos buffer1)
	{
		long l1 = varDefNum1.getAsDecodedInt(buffer1);
		long l2 = getAsDecodedInt(buffer2);
		return internalCompare(l1, l2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefX varDef1, VarBufferPos buffer1)
	{
		// see http://publibz.boulder.ibm.com/cgi-bin/bookmgr_OS390/BOOKS/IGYLR205/6.1.6.5.3?SHELF=&DT=20000927030801&CASE=
		// see http://h71000.www7.hp.com/DOC/73final/6297/6297_profile_010.html#group_items_sec
		// If the nonnumeric operand is an elementary item or a literal, the compiler treats the numeric operand as if it had been moved into an alphanumeric data item the same size as the numeric operand and then compared. This causes any operational sign, whether carried as a separate character or as an overpunched character, to be stripped from the numeric item so that it appears to be an unsigned quantity. 
		// In addition, if the PICTURE character-string of the numeric item contains trailing P characters, indicating that there are assumed integer positions that are not actually present, they are filled with zero digits. Thus, an item with a PICTURE character-string of S9999PPP is moved to a temporary location where it is described as 9999999. If its value is 432J (--4321), the value in the temporary location will be 4321000. The numeric digits take part in the comparison.
		//String cs1 = varDef1.getRawStringExcludingHeader(buffer1);
		CStr cs1 = buffer1.getBodyCStr(varDef1);
//		int n2 = getUnsignedInt(buffer2);
//		String cs2 = getStringRightPadded(n2, ' ', varDef1.getTotalSize());
		CStr cs2 = getAsAlphaNumString(buffer2);
		return internalCompare(mode, cs1, cs2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefFPacAlphaNum varDef1, VarBufferPos buffer1)
	{
		// see http://publibz.boulder.ibm.com/cgi-bin/bookmgr_OS390/BOOKS/IGYLR205/6.1.6.5.3?SHELF=&DT=20000927030801&CASE=
		// see http://h71000.www7.hp.com/DOC/73final/6297/6297_profile_010.html#group_items_sec
		// If the nonnumeric operand is an elementary item or a literal, the compiler treats the numeric operand as if it had been moved into an alphanumeric data item the same size as the numeric operand and then compared. This causes any operational sign, whether carried as a separate character or as an overpunched character, to be stripped from the numeric item so that it appears to be an unsigned quantity. 
		// In addition, if the PICTURE character-string of the numeric item contains trailing P characters, indicating that there are assumed integer positions that are not actually present, they are filled with zero digits. Thus, an item with a PICTURE character-string of S9999PPP is moved to a temporary location where it is described as 9999999. If its value is 432J (--4321), the value in the temporary location will be 4321000. The numeric digits take part in the comparison.
		//String cs1 = varDef1.getRawStringExcludingHeader(buffer1);
		CStr cs1 = buffer1.getBodyCStr(varDef1);
//		int n2 = getUnsignedInt(buffer2);
//		String cs2 = getStringRightPadded(n2, ' ', varDef1.getTotalSize());
		CStr cs2 = getAsAlphaNumString(buffer2);
		return internalCompare(mode, cs1, cs2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefFPacRaw varDef1, VarBufferPos buffer1)
	{
		// see http://publibz.boulder.ibm.com/cgi-bin/bookmgr_OS390/BOOKS/IGYLR205/6.1.6.5.3?SHELF=&DT=20000927030801&CASE=
		// see http://h71000.www7.hp.com/DOC/73final/6297/6297_profile_010.html#group_items_sec
		// If the nonnumeric operand is an elementary item or a literal, the compiler treats the numeric operand as if it had been moved into an alphanumeric data item the same size as the numeric operand and then compared. This causes any operational sign, whether carried as a separate character or as an overpunched character, to be stripped from the numeric item so that it appears to be an unsigned quantity. 
		// In addition, if the PICTURE character-string of the numeric item contains trailing P characters, indicating that there are assumed integer positions that are not actually present, they are filled with zero digits. Thus, an item with a PICTURE character-string of S9999PPP is moved to a temporary location where it is described as 9999999. If its value is 432J (--4321), the value in the temporary location will be 4321000. The numeric digits take part in the comparison.
		//String cs1 = varDef1.getRawStringExcludingHeader(buffer1);
		CStr cs1 = buffer1.getBodyCStr(varDef1);
//		int n2 = getUnsignedInt(buffer2);
//		String cs2 = getStringRightPadded(n2, ' ', varDef1.getTotalSize());
		CStr cs2 = getAsAlphaNumString(buffer2);
		return internalCompare(mode, cs1, cs2);
	}
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefG varDef1, VarBufferPos buffer1)
	{
		// see http://h71000.www7.hp.com/DOC/73final/6297/6297_profile_010.html#group_items_sec
		// If the nonnumeric operand is a group item, the compiler treats the numeric operand as if it had been moved into a group item the same size as the numeric operand and then compared. This is equivalent to a group move. 
		// The compiler ignores the description of the numeric item (except for length) and, therefore, includes in its length any operational sign, whether carried as a separate character or as an overpunched character. Overpunched characters are never ASCII numeric digits. They are characters ranging from A to R, left brace ({), or right brace (}). Thus, the sign and the digits, stored as ASCII bytes, take part in the comparison, and zeros are not supplied for P characters in the PICTURE character-string. 
		//String cs1 = varDef1.getRawStringExcludingHeader(buffer1);
		CStr cs1 = buffer1.getBodyCStr(varDef1);
		int n2 = getAsDecodedInt(buffer2);
		//String cs2 = getStringRightPadded(n2, ' ', varDef1.getTotalSize());
		return StringAsciiEbcdicUtil.compare(mode, cs1, String.valueOf(n2));
	}	
	
	int compare(ComparisonMode mode, VarBufferPos buffer2, VarDefNumEdited varDef1, VarBufferPos buffer1)
	{
		assertIfFalse(false);
		// TODO how to compare with num edited ?
		return 0;
	}
	
	boolean isTypedLongVarCharLength()	// The type can contain a long varchar length part
	{
		if(m_nNbDigitInteger == 4)
			return true;
		return false;
	}
	
	public String digits(VarBufferPos buffer)
	{
		int nValue = internalReadIntSignComp4(buffer);
		if(nValue < 0)
			nValue = -nValue;
		CStrNumber csNum = TempCacheLocator.getTLSTempCache().getCStrNumber();
		csNum.getAsAbsoluteIntComp0String(nValue, m_nNbDigitInteger);
		return csNum.getAsString();
	}
	
	public int getNbDigitDecimal()
	{
		return 0;
	}
	
	boolean isConvertibleInEbcdic()
	{
		return false;
	}
	
	public int getTypeId()
	{
		return VarTypeId.VarDefNumIntSignComp4TypeId;
	}

	public BtreeSegmentKeyTypeFactory getSegmentKeyTypeFactory()
	{
		return VarTypeId.m_segmentKeyTypeFactorySignComp4;
	}

	public boolean isEbcdicAsciiConvertible()
	{
		return false;
	}
	
	protected void adjustCustomProperty(VarDefBuffer varDefBufferCopySingleItem)
	{
		VarDefNumIntSignComp4 varDefCopy = (VarDefNumIntSignComp4)varDefBufferCopySingleItem;
		varDefCopy.m_nNbDigitInteger = m_nNbDigitInteger;
	}
	
	protected void adjustCustomPropertyForCharGetAt(VarDefBuffer varDefBufferCopySingleItem)
	{
		VarDefNumIntSignComp4 varDefCopy = (VarDefNumIntSignComp4)varDefBufferCopySingleItem;
		varDefCopy.m_nNbDigitInteger = 1;
	}

	boolean isNumeric(VarBufferPos buffer)
	{
		return true;	// All byte values are valid
	}
	
	private int m_nNbDigitInteger;
}
