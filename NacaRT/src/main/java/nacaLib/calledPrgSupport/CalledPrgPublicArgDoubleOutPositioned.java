/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.calledPrgSupport;

import nacaLib.varEx.Var;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: CalledPrgPublicArgDoubleOutPositioned.java,v 1.2 2007/09/21 15:11:30 u930bm Exp $
 */
public class CalledPrgPublicArgDoubleOutPositioned extends BaseCalledPrgPublicArgPositioned
{
	CalledPrgPublicArgDoubleOutPositioned(double dValue[], boolean bInOut)
	{
		super(bInOut);
		m_dValue = dValue;
	}
	
	public void MapOn(Var varLinkageSection)
	{
		varLinkageSection.set(m_dValue[0]);
	}
	
	public void doFillWithVar(Var varSource)
	{
		m_dValue[0] = varSource.getDouble();		
	}
	
	public int getParamLength()
	{
		return 8;
	}
	
	private double m_dValue[];
}