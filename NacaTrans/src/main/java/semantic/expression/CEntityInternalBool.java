/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on 7 juil. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package semantic.expression;

import generate.CBaseLanguageExporter;
import semantic.CDataEntity;
import utils.CObjectCatalog;

/**
 * @author U930CV
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class CEntityInternalBool extends CDataEntity
{

	/**
	 * @param l
	 * @param name
	 * @param cat
	 * @param out
	 */
	public CEntityInternalBool(String name, CObjectCatalog cat, CBaseLanguageExporter out)
	{
		super(0, name, cat, out);
	}

	/* (non-Javadoc)
	 * @see semantic.CDataEntity#GetDataType()
	 */
	public CDataEntityType GetDataType()
	{
		return CDataEntityType.VAR ;
	}

	/* (non-Javadoc)
	 * @see semantic.CDataEntity#HasAccessors()
	 */
	public boolean HasAccessors()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see semantic.CDataEntity#ExportWriteAccessorTo(java.lang.String)
	 */
	public String ExportWriteAccessorTo(String value)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see semantic.CDataEntity#GetConstantValue()
	 */
	public String GetConstantValue()
	{
		return "" ;
	}

}
