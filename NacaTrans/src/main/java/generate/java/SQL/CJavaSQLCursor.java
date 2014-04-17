/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on Oct 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package generate.java.SQL;

import generate.CBaseLanguageExporter;
import semantic.SQL.CEntitySQLCursor;
import utils.CObjectCatalog;

/**
 * @author U930CV
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CJavaSQLCursor extends CEntitySQLCursor
{
	/**
	 * @param name
	 * @param cat
	 * @param out
	 */
	public CJavaSQLCursor(String name, CObjectCatalog cat, CBaseLanguageExporter out)
	{
		super(name, cat, out);
	}

	public CDataEntityType GetDataType()
	{
		return null;
	}

	public String ExportReference(int nLine)
	{
		return FormatIdentifier(GetName());
	}
	protected void DoExport()
	{
		// unused
	}
	public boolean isValNeeded()
	{
		return false;
	}

}
