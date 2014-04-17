/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on 11 ao�t 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package generate.java.forms;

import generate.CBaseLanguageExporter;
import semantic.CDataEntity;
import semantic.forms.CEntityFieldFlag;
import utils.CObjectCatalog;

/**
 * @author sly
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CJavaFieldFlag extends CEntityFieldFlag
{

	/**
	 * @param l
	 * @param name
	 * @param cat
	 * @param out
	 * @param owner
	 */
	public CJavaFieldFlag(int l, String name, CObjectCatalog cat, CBaseLanguageExporter out, CDataEntity owner)
	{
		super(l, name, cat, out, owner);
	}
	public String ExportReference(int nLine)
	{
		return m_Reference.ExportReference(getLine()) + ".getFlag()" ;
		//e.WriteWord(".GetProtected()") ;
	}
	public boolean HasAccessors()
	{
		return true ;
	}
	protected void DoExport()
	{
		// unsued
	}
	public String ExportWriteAccessorTo(String value)
	{
		return "moveFlag(" + value + ", " + m_Reference.ExportReference(getLine()) + ") ;" ;
	}
	public boolean isValNeeded()
	{
		return false;
	}

	
	/* (non-Javadoc)
	 * @see semantic.CBaseDataEntity#GetDataType()
	 */
	public CDataEntityType GetDataType()
	{
		return CDataEntityType.FIELD ;
	}

}
