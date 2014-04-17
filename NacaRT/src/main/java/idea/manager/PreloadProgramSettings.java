/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 * 
 */
package idea.manager;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: PreloadProgramSettings.java,v 1.1 2006/12/15 14:26:25 u930di Exp $
 */
public class PreloadProgramSettings
{
	private String m_csProgramName = null;
	private int m_nQty = 0;
	
	PreloadProgramSettings(String csName)
	{
		m_csProgramName = csName;
		m_nQty = 1;
	}
	
	void setQty(int nQty)
	{
		m_nQty = nQty;
	}
	
	String getName()
	{
		return m_csProgramName;
	}
	
	int getQty()
	{
		return m_nQty;
	}	
}
