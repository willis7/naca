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
package generate.java.expressions;

import generate.CBaseLanguageExporter;
import semantic.CDataEntity;
import semantic.expression.CEntityLengthOf;
import utils.CObjectCatalog;

/**
 * @author U930CV
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CJavaLengthOf extends CEntityLengthOf
{
	/**
	 * @param cat
	 * @param out
	 * @param data
	 */
	public CJavaLengthOf(CObjectCatalog cat, CBaseLanguageExporter out, CDataEntity data)
	{
		super(cat, out, data);
	}
	/* (non-Javadoc)
	 * @see semantic.CBaseDataEntity#ExportReference(generate.CBaseLanguageExporter)
	 */
	public String ExportReference(int nLine)
	{
		return "lengthOf(" + m_Reference.ExportReference(getLine()) + ")";
	}
	public boolean isValNeeded()
	{
		return true;
	}

}
