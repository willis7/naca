/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.varEx;

public class DeclareTypeFPacSignComp4 extends DeclareType9
{
	public DeclareTypeFPacSignComp4()
	{
	}
	
	public void set(VarLevel varLevel, int nNbDigitIntegerPart)
	{		
		set(varLevel, true, nNbDigitIntegerPart, 0);
		comp();
	}
}