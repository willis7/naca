/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
package generate.fpacjava;

import generate.CBaseLanguageExporter;
import semantic.CEntityComment;
import utils.CObjectCatalog;

/**
 * @author S. Charton
 * @version $Id: CFPacJavaComment.java,v 1.4 2007/06/28 06:19:46 u930bm Exp $
 */
public class CFPacJavaComment extends CEntityComment
{

	/**
	 * @param l
	 * @param cat
	 * @param out
	 * @param comment
	 */
	public CFPacJavaComment(int l, CObjectCatalog cat, CBaseLanguageExporter out, String comment)
	{
		super(l, cat, out, comment);
	}

	/**
	 * @see semantic.CBaseLanguageEntity#DoExport()
	 */
	@Override
	protected void DoExport()
	{
		String cs = m_csComment ;
		if (cs.indexOf('\n') > 0 || cs.indexOf('\r') > 0)
		{
			cs = cs.replaceAll("\n", "0x000A").replaceAll("\r", "Ox000D") ;
		}
		WriteLine("// " + cs) ;	
	}
	/**
	 * @see semantic.CEntityComment#ExportReference(getLine())
	 */
	@Override
	public String ExportReference(int nLine)
	{
		String cs = m_csComment ;
		if (cs.indexOf('\n') > 0 || cs.indexOf('\r') > 0)
		{
			cs = cs.replaceAll("\n", "0x000A").replaceAll("\r", "Ox000D") ;
		}
		return "  // " + cs ;	
	}

}
