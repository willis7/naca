/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
package generate.java.SQL;

import generate.CBaseLanguageExporter;
import semantic.SQL.CEntitySQLExecute;
import utils.CObjectCatalog;

/**
 * @author S. Charton
 * @version $Id: CJavaSQLExecute.java,v 1.3 2007/06/28 06:19:46 u930bm Exp $
 */
public class CJavaSQLExecute extends CEntitySQLExecute
{

	/**
	 * @param line
	 * @param cat
	 * @param out
	 */
	public CJavaSQLExecute(int line, CObjectCatalog cat, CBaseLanguageExporter out)
	{
		super(line, cat, out);
	}

	/**
	 * @see semantic.CBaseLanguageEntity#DoExport()
	 */
	@Override
	protected void DoExport()
	{
		WriteWord("sql(\"EXECUTE IMMEDIATE #1\").param(1, "+m_eVariable.ExportReference(getLine())+")");
		String csSQLErrorWarningStatement = m_ProgramCatalog.getSQLWarningErrorStatement();
		if(csSQLErrorWarningStatement != null)
		{
			WriteWord(csSQLErrorWarningStatement);
		}
		WriteWord(" ;") ;
		WriteEOL() ;
	}

}
