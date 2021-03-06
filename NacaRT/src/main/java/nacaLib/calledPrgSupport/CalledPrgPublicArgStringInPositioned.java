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
 * @version $Id: CalledPrgPublicArgStringInPositioned.java,v 1.2 2007/09/21 15:11:30 u930bm Exp $
 */
public class CalledPrgPublicArgStringInPositioned extends BaseCalledPrgPublicArgPositioned
{
	CalledPrgPublicArgStringInPositioned(String csValue)
	{
		super();
		m_csValue = csValue;
	}
	
	public void MapOn(Var varLinkageSection)
	{
		varLinkageSection.set(m_csValue);
	}
	
	public void doFillWithVar(Var varSource)
	{
		m_csValue = varSource.getString();		
	}
	
	public int getParamLength()
	{
		return m_csValue.length();
	}
	
	private String m_csValue = null;
}
