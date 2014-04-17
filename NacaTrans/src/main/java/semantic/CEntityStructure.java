/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on 2 ao�t 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package semantic;

import generate.*;

import java.util.Vector;

import jlib.misc.NumberParser;

import parser.expression.CExpression;

import semantic.expression.CBaseEntityExpression;
import utils.*;

/**
 * @author sly
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class CEntityStructure extends CEntityAttribute
{

	/**
	 * @param name
	 * @param cat
	 */
	public CEntityStructure(int l, String name, CObjectCatalog cat, CBaseLanguageExporter out, String level)
	{
		super(l, name, cat, out);
		if (name.equals(""))
		{
			m_bFiller = true ;
			name = GetDefaultName() ;
			if (!name.equals(""))
			{
				SetName(name) ;
			}
		}
		m_csLevel = level ;
	}
	protected boolean m_bFiller = false ;
	public CDataEntity GetArrayReference(Vector v, CBaseEntityFactory factory) 
	{
		CEntityArrayReference e = factory.NewEntityArrayReference(getLine()) ;
		e.SetReference(this) ;
		for (int i=0; i<v.size(); i++)
		{
			CExpression expr = (CExpression)v.get(i);
			CBaseEntityExpression exp = expr.AnalyseExpression(factory);
			e.AddIndex(exp);
		}
		return e ;
	};
	public void SetTableSize(CDataEntity term)
	{
		m_TableSize = term ;
	}
	public void SetTableSizeDepending(CDataEntity term, CDataEntity dep)
	{
		m_TableSize = term ;
		m_TableSizeDepending = dep ;
		m_bIsVariableLenght = true ;		
	}
	public void SetRedefine(CDataEntity e)
	{
		m_RefRedefine = e ;
	}
	public String m_csLevel = "" ;
	protected CDataEntity m_TableSize = null ;
	protected CDataEntity m_TableSizeDepending = null ;
	protected boolean m_bIsVariableLenght = false ;
	protected CDataEntity m_RefRedefine = null ;
	public void AddChild(CBaseLanguageEntity e)
	{
		super.AddChild(e) ;
		int n = e.GetInternalLevel() ;
		if (n>0)
		{
			if (m_nActualSubLevel == 0)
			{
				m_nActualSubLevel = n ;
			}
			else if (m_nActualSubLevel != n)
			{
				Transcoder.logWarn(e.getLine(), "WARNING : bad sub-level for structure : expecting "+m_nActualSubLevel+" ; found "+n) ;
			}
		}
	}
	protected int m_nActualSubLevel = 0 ;
	public int GetInternalLevel()
	{
		return Integer.parseInt(m_csLevel) ;
	} 
	public CEntityProcedureSection getSectionContainer()
	{
		return null ;
	}
	public boolean IsRedefine()
	{
		return m_RefRedefine != null ;
	} 
	public boolean ignore()
	{
//		boolean ignore = m_arrActionsReading.size()== 0 ;
//		ignore &= m_arrActionsWriting.size() == 0 ;
//		ignore &= m_arrTestsAsValue.size() == 0 ;
//		ignore &= m_arrTestsAsVar.size() == 0 ;
//		ignore &= (m_lstChildren.size() == 0 || isChildrenIgnored()) ;
//		if (ignore)
//		{
//			int n=0; 
//		}
//		return ignore ;
		return m_bIgnore ;
	}
	public void Clear()
	{
		super.Clear();
		if (m_RefRedefine != null)
		{
			//m_RefRedefine.Clear() ;
			m_RefRedefine = null ;
		}
		if (m_TableSize != null)
		{
			m_TableSize.Clear() ;
		}
		m_TableSize = null ;
	}
	protected void RegisterMySelfToCatalog()
	{
		if (m_parent != null)
		{
			m_ProgramCatalog.RegisterDataEntity(GetName(), this) ;
		}
	}
	public void SetParent(CBaseLanguageEntity e)
	{
		super.SetParent(e) ;
		RegisterMySelfToCatalog() ;
	}
	public int getActualSubLevel()
	{
		return m_nActualSubLevel ;
	}
	/**
	 * @return
	 */
	public CEntityIndex getOccursIndex()
	{
		return m_OccursIndex;
	}
	/**
	 * @param index
	 */
	public void setOccursIndex(CEntityIndex index)
	{
		m_OccursIndex = index ;		
	}
	protected CEntityIndex m_OccursIndex = null ;
	
	@Override
	public CDataEntity FindFirstDataEntityAtLevel(int level)
	{
		if (NumberParser.getAsInt(m_csLevel) == level)
		{
			return this ;
		}
		return super.FindFirstDataEntityAtLevel(level) ;
	}
	public int getTableSizeAsInt()
	{
		return NumberParser.getAsInt(m_TableSize.GetConstantValue()) ;
	}
	public CDataEntity getTableSize()
	{
		return m_TableSize ;
	}
	public boolean canOwnTableSize()
	{
		return true;
	}
	public int getVariableSize()
	{
		return m_Length ;
	}
	public CDataEntity getTableSizeDepending()
	{
		return m_TableSizeDepending;
	}
}
