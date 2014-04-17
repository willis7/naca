/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.varEx;

import jlib.misc.AsciiEbcdicConverter;

public class VarNumDecSignComp4 extends VarNum
{
	VarNumDecSignComp4(DeclareType9 declareType9)
	{
		super(declareType9);
	}
	
	protected VarNumDecSignComp4()
	{
		super();
	}
	
	protected VarBase allocCopy()
	{
		VarNumDecSignComp4 v = new VarNumDecSignComp4();
		return v;
	}
	
	public boolean DEBUGisStorageAscii()
	{
		return false;
	}
	
	public int compareTo(ComparisonMode mode, String csValue)
	{
		double dValue = 0;
		try
		{
			dValue = new Double(csValue).doubleValue();
		}
		catch (Exception ex)
		{	
		}
		return compareTo(dValue);
	}
	
	public int compareTo(int nValue)
	{
		double dValue = nValue;
		return compareTo(dValue);
	}
		
	public int compareTo(double dValue)
	{
		double dVarValue = getDouble();
		double d = dVarValue - dValue;
		if(d < -0.00001)	//Consider epsilon precision at 10 e-5 
			return -1;
		else if(d > 0.00001)	//Consider epsilon precision at 10 e-5
			return 1;
		return 0;			
	}

	protected byte[] convertUnicodeToEbcdic(char [] tChars)
	{
		return AsciiEbcdicConverter.noConvertUnicodeToEbcdic(tChars);
	}
	
	protected char[] convertEbcdicToUnicode(byte[] tBytes)
	{
		return ebcdicToUnicodeInBinaryFormat(tBytes);
	}
	

	public VarType getVarType()
	{
		return VarType.VarNumDecSignComp4;
	}
}
