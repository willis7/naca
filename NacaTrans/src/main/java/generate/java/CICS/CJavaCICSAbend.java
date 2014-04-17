/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on Sep 29, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package generate.java.CICS;

import generate.CBaseLanguageExporter;
import semantic.CICS.CEntityCICSAbend;
import utils.CObjectCatalog;

/**
 * @author U930CV
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CJavaCICSAbend extends CEntityCICSAbend
{
	/**
	 * @param line
	 * @param cat
	 * @param out
	 */
	public CJavaCICSAbend(int line, CObjectCatalog cat, CBaseLanguageExporter out)
	{
		super(line, cat, out);
	}

	protected void DoExport()
	{
		String cs = "CESM.abend(" ;
		if (m_ABCode!= null)
		{
			cs += m_ABCode.ExportReference(getLine()) ;
		}
		WriteLine(cs + ") ;");
	}

}
