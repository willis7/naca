/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
package semantic.Verbs;

import generate.CBaseLanguageExporter;
import semantic.CBaseActionEntity;
import semantic.CDataEntity;
import semantic.CEntityFileDescriptor;
import utils.CObjectCatalog;

public abstract class CEntityWriteFile extends CBaseActionEntity
{

	public CEntityWriteFile(int line, CObjectCatalog cat, CBaseLanguageExporter out)
	{
		super(line, cat, out);
	}

	public void setFileDescriptor(CEntityFileDescriptor efd, CDataEntity data)
	{
		m_eFileDescriptor = efd ;
		m_eDataFrom = data ;
	}
	public void SetAfter(CDataEntity after)
	{
		m_after = after;
	}
	protected CEntityFileDescriptor m_eFileDescriptor = null  ;
	protected CDataEntity m_eDataFrom = null ;
	protected CDataEntity m_after ;

}
