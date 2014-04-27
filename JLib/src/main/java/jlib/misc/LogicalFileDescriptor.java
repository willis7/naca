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

/**
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: LogicalFileDescriptor.java,v 1.16 2007/10/25 15:13:11 u930di Exp $
 */
public class LogicalFileDescriptor {
    private boolean m_bDummyFile = false;
    private String m_csLogicalName = null;
    private String m_csPath = null;
    private boolean m_bExt = false;
    private boolean m_bEbcdic = false;
    private boolean m_bVariableLength = false;
    private boolean m_bVariableLength4BytesLF = false;
    private RecordLengthDefinition m_recordLengthDefinition = null;
    private int m_nFileHeaderLength = 0;
    private RecordLengthInfoDefinitionType m_recordLengthInfoDefinitionType = null;

    public LogicalFileDescriptor(String csLogicalName, String csPhysicalDesc) {
        m_csLogicalName = csLogicalName;
        if (BaseDataFile.isNullFile(csPhysicalDesc))    //	if(m_csLogicalName.equalsIgnoreCase("wrk/nullfile"))
            m_bDummyFile = true;
        else {
            m_bDummyFile = false;
            fill(csPhysicalDesc);
        }
    }

    public boolean isDummyFile() {
        return m_bDummyFile;
    }

    public boolean isEbcdic() {
        return m_bEbcdic;
    }

    public boolean getExt() {
        return m_bExt;
    }

    public String getPath() {
        return m_csPath;
    }

    public RecordLengthDefinition getRecordLengthDefinition() {
        return m_recordLengthDefinition;
    }

    public void setRecordLengthDefinition(RecordLengthDefinition recLengthDefSource) {
        m_recordLengthDefinition = recLengthDefSource;
    }

    public void fill(String csPhysicalDesc) {
        int nIndex = csPhysicalDesc.indexOf(",");
        if (nIndex >= 0) {
            m_csPath = csPhysicalDesc.substring(0, nIndex).trim();
            csPhysicalDesc = csPhysicalDesc.substring(nIndex + 1);
            nIndex = csPhysicalDesc.indexOf(",");
            while (nIndex != -1) {
                String csWord = csPhysicalDesc.substring(0, nIndex).trim();
                manageOptionalWord(csWord);

                csPhysicalDesc = csPhysicalDesc.substring(nIndex + 1);
                nIndex = csPhysicalDesc.indexOf(",");
            }
            manageOptionalWord(csPhysicalDesc);
        } else
            m_csPath = csPhysicalDesc.trim();
    }

    private void manageOptionalWord(String csWord) {
        if (csWord.equalsIgnoreCase("ext"))
            m_bExt = true;
        else if (csWord.equalsIgnoreCase("ebcdic"))
            m_bEbcdic = true;
        else if (csWord.equalsIgnoreCase("ascii"))
            m_bEbcdic = false;
        else if (csWord.equalsIgnoreCase("fb")) {
            m_bVariableLength = false;
            m_bVariableLength4BytesLF = false;
            m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileDescriptorDef;
        } else if (csWord.equalsIgnoreCase("vb")) {
            m_bVariableLength = true;
            m_bVariableLength4BytesLF = true;
            m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileDescriptorDef;
        } else if (csWord.equalsIgnoreCase("vh")) {
            m_bVariableLength = true;
            m_bVariableLength4BytesLF = false;
            m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileDescriptorDef;
        } else    // Maybe record length if all digits
        {
            if (StringUtil.isAllDigits(csWord)) {
                m_recordLengthDefinition = new RecordLengthDefinition(NumberParser.getAsInt(csWord));
                m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileDescriptorDef;
            }
        }
    }

    public void setVariableLength() {
        m_bVariableLength = true;
    }

    public boolean isVariableLength() {
        return m_bVariableLength;
    }

    public boolean isVariableLength4BytesHeaderWithLF() {
        return m_bVariableLength4BytesLF;
    }

    public String toString() {
        String cs = "";
        if (m_csPath != null)
            cs += "Path=" + m_csPath;
        cs += " Ext=" + m_bExt;
        cs += " Ebcdic=" + m_bEbcdic;
        cs += " VariableLength=" + m_bVariableLength;
        cs += " HAs 4 bytes header and LF=" + m_bVariableLength4BytesLF;
        if (m_recordLengthDefinition != null)
            cs += " RecordLength=" + m_recordLengthDefinition.toString();
        else
            cs += " NoRecordLengthDefined";
        return cs;
    }

    public String getName() {
        String cs = "";
        if (m_csLogicalName != null) {
            cs = m_csLogicalName;
            cs += getPathName();
        } else {
            cs = "UnkownLogicalName";
            cs += getPathName();
        }
        return cs;
    }

    private String getPathName() {
        if (m_bDummyFile)
            return " (Dummy file) ";
        if (m_csPath != null)
            return " (" + m_csPath + ") ";
        return " (Unkown physical path) ";
    }

    public boolean writeFileHeader(BaseDataFile dataFile) {
        if (dataFile != null && dataFile.isOpen() && dataFile.isWritable() && !dataFile.isUpdateable())    // Do not write header for files in rewrite mode
        {
            String csFileHeader = getAsFileHeaderString();
            dataFile.writeRecord(csFileHeader);
            return true;
        }
        return false;
    }

    private String getAsFileHeaderString() {
        String cs = null;
        if (m_bEbcdic)
            cs = "<FileHeader Version=\"1\" Encoding=\"ebcdic\" ";
        else
            cs = "<FileHeader Version=\"1\" Encoding=\"ascii\" ";

        if (m_bVariableLength) {
            if (m_bVariableLength4BytesLF)
                cs += "Length=\"VB\"";
            else
                cs += "Length=\"VH\"";
        } else {
            if (m_recordLengthDefinition != null)
                cs += "Length=\"" + m_recordLengthDefinition.toString() + "\"";
            else
                cs += "Length=\"Unknown\"";
        }
        cs += "/>";
        return cs;
    }

    private boolean setFromFileHeaderString(String cs) {
        if (cs.startsWith("<FileHeader") && cs.endsWith("/>")) {
            String csVersion = StringUtil.getUncotedParameterValue(cs, "Version");
            if (csVersion != null) {
                if (!StringUtil.isEmpty(csVersion)) {
                    int nVersion = NumberParser.getAsInt(csVersion);
                    if (nVersion == 1) {
                        String csEncoding = StringUtil.getUncotedParameterValue(cs, "Encoding");
                        if (!StringUtil.isEmpty(csEncoding)) {
                            if (csEncoding.equalsIgnoreCase("ebcdic"))
                                m_bEbcdic = true;
                            if (csEncoding.equalsIgnoreCase("ascii"))
                                m_bEbcdic = false;
                        }

                        String csLength = StringUtil.getUncotedParameterValue(cs, "Length");
                        if (!StringUtil.isEmpty(csLength)) {
                            if (csLength.equalsIgnoreCase("VB")) {
                                m_bVariableLength = true;
                                m_bVariableLength4BytesLF = true;
                                m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileHeaderDef;
                                m_recordLengthDefinition = null;
                            } else if (csLength.equalsIgnoreCase("VH")) {
                                m_bVariableLength = true;
                                m_bVariableLength4BytesLF = false;
                                m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileHeaderDef;
                                m_recordLengthDefinition = null;
                            } else if (StringUtil.isAllDigits(csLength)) {
                                m_bVariableLength = false;
                                m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.FileHeaderDef;
                                int nLength = NumberParser.getAsInt(csLength);
                                m_recordLengthDefinition = new RecordLengthDefinition(nLength);
                            }
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean readFileHeader(BaseDataFile dataFile) {
        if (dataFile != null && dataFile.isOpen() && dataFile.isReadable()) {
            String cs = dataFile.unbufferedReadAheadLine(100);
            if (cs != null) {
                boolean bFileHeaderFound = setFromFileHeaderString(cs);
                if (bFileHeaderFound) {
                    m_nFileHeaderLength = dataFile.skipFileHeader(cs);
                    return true;
                }
            }
            // else	// We are already atr the beginning of the file
            //{
            //	Do nothing
            //}
        }
        return false;
    }

    public void inheritSettings(LogicalFileDescriptor logicalFileDescriptorSource) {
        RecordLengthDefinition recLengthDefSource = logicalFileDescriptorSource.getRecordLengthDefinition();
        setRecordLengthDefinition(recLengthDefSource);

        m_bVariableLength = logicalFileDescriptorSource.m_bVariableLength;
        m_bVariableLength4BytesLF = logicalFileDescriptorSource.m_bVariableLength4BytesLF;
        m_recordLengthInfoDefinitionType = logicalFileDescriptorSource.m_recordLengthInfoDefinitionType;
    }

    public int getFileHeaderLength() {
        return m_nFileHeaderLength;
    }

    public RecordLengthInfoDefinitionType recordLengthInfoDefitionType() {
        return m_recordLengthInfoDefinitionType;
    }

    public boolean isLengthInfoDefined() {
        if (m_recordLengthInfoDefinitionType != null)
            return true;
        return false;
    }

    public boolean tryAutoDetermineRecordLength(BaseDataFile dataFile) {
        if (!isLengthInfoDefined())    // File header must have already been tried to read
        {
            if (dataFile.savePosition(65536 * 10)) {
                boolean b = tryDetermineVariableLengthRecord(dataFile);
                if (!b) {
                    dataFile.returnAtSavedPosition();
                    b = tryDetermineFixedLengthRecord(dataFile);
                }

                dataFile.returnAtSavedPosition();
                return b;
            }
        }
        return true;
    }

    private boolean tryDetermineVariableLengthRecord(BaseDataFile dataFile) {
        // the m_bVariableLength4BytesLF is not supported in autodermination
        try {
            int nNbRecordHeaderOk = 0;
            int nNbRecordControled = 0;
            int nNbRecordHeaderChecked = 0;
            int nNbRecordHeaderNotOk = 0;
            for (; nNbRecordHeaderChecked < 3; nNbRecordHeaderChecked++)    // Check on 3 records if there is a LF at offset nLength + 1
            {
                LineRead recordHeader = dataFile.readBuffer(4, false);
                if (recordHeader != null) {
                    nNbRecordControled++;
                    int nLength = recordHeader.getAsLittleEndingUnsignBinaryInt();
                    if (nLength < 65536) {
                        LineRead recordBody = dataFile.readBuffer(nLength, true);
                        if (recordBody != null) {
                            if (recordBody.isTrailingLF())
                                nNbRecordHeaderOk++;
                            else
                                nNbRecordHeaderNotOk++;
                        }
                    }
                }
            }
            if (nNbRecordHeaderOk == nNbRecordControled && nNbRecordHeaderOk > 0 && nNbRecordHeaderNotOk == 0)    // All record cheked are ok, even if less than 3 records in the file !
            {
                m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.AutoDetermination;
                m_bVariableLength = true;
                m_bVariableLength4BytesLF = true;
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }


    private boolean tryDetermineFixedLengthRecord(BaseDataFile dataFile) {
        int tnRecordLengthFound[] = new int[10];
        int tnRecordLengthQty[] = new int[10];
        int nNbRecordRead = 0;
        int nNbQtyfound = 0;
        LineRead lastLineRead = dataFile.readNextUnixLine();
        for (; nNbRecordRead < 10 && lastLineRead != null; nNbRecordRead++) {
            boolean b = false;
            int nLength = lastLineRead.getBodyLength();
            for (int n = 0; n < nNbQtyfound; n++) {
                if (tnRecordLengthFound[n] == nLength) {
                    tnRecordLengthQty[n]++;
                    b = true;
                    break;
                }
            }
            if (!b) {
                tnRecordLengthFound[nNbQtyfound] = nLength;
                tnRecordLengthQty[nNbQtyfound] = 1;
                nNbQtyfound++;
            }

            lastLineRead = dataFile.readNextUnixLine();
        }

        int nMaxQty = -1;
        int nLengthFound = -1;
        for (int n = 0; n < nNbQtyfound; n++) {
            if (tnRecordLengthQty[n] > nMaxQty) {
                nMaxQty = tnRecordLengthQty[n];
                nLengthFound = tnRecordLengthFound[n];
            }
        }
        dataFile.setEOF(false);

        if (nLengthFound != -1) {
            m_recordLengthInfoDefinitionType = RecordLengthInfoDefinitionType.AutoDetermination;
            m_bVariableLength = false;
            m_bVariableLength4BytesLF = false;
            m_recordLengthDefinition = new RecordLengthDefinition(nLengthFound);    // Length found included trailing LF
        }
        if (nNbQtyfound == 1)
            return true;
        else
            return false;
    }

}
