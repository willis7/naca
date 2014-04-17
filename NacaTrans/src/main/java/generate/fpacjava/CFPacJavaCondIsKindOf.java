/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
package generate.fpacjava;

import semantic.expression.CBaseEntityCondition;
import semantic.expression.CEntityCondIsKindOf;

/**
 * @author S. Charton
 * @version $Id: CFPacJavaCondIsKindOf.java,v 1.2 2007/06/28 06:19:46 u930bm Exp $
 */
public class CFPacJavaCondIsKindOf extends CEntityCondIsKindOf
{

	public int GetPriorityLevel()
	{
		return 7;
	}
	public CBaseEntityCondition GetOppositeCondition()
	{
		CFPacJavaCondIsKindOf not = new CFPacJavaCondIsKindOf() ;
		not.m_bIsAlphabetic = m_bIsAlphabetic ;
		not.m_bIsLower = m_bIsLower ;
		not.m_bIsNumeric = m_bIsNumeric ;
		not.m_bIsUpper = m_bIsUpper ;
		not.m_bOpposite = ! m_bOpposite ;
		not.m_Reference = m_Reference ;
		return not;
	}
	public String Export()
	{
		String cs = "is" ;
		if (m_bOpposite)
		{
			cs += "Not" ;
		}
		if (m_bIsNumeric)
		{
			cs += "Numeric(";
		}
		else if (m_bIsAlphabetic)
		{
			cs += "Alphabetic(";
		}
		if (m_Reference != null)
		{
			cs += m_Reference.ExportReference(getLine());
		}
		else
		{
			cs += "[UNDEFINED]" ;
		}
		cs += ")";
		return cs ;
	}

}
