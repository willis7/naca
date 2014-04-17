/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on Jul 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package parser.Cobol.elements;

import java.util.ArrayList;
import java.util.List;

import lexer.CBaseToken;
import lexer.CTokenType;
import lexer.Cobol.CCobolKeywordList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import parser.CIdentifier;
import parser.Cobol.CCobolElement;
import semantic.CBaseActionEntity;
import semantic.CBaseLanguageEntity;
import semantic.CBaseEntityFactory;
import semantic.CDataEntity;
import utils.CGlobalEntityCounter;
import utils.Transcoder;

/**
 * @author U930CV
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CGoto extends CCobolElement
{
	/**
	 * @param line
	 */
	public CGoto(int line) {
		super(line);
	}
	/* (non-Javadoc)
	 * @see parser.CLanguageElement#Parse(lexer.CTokenList)
	 */
	protected boolean DoParsing()
	{
		CBaseToken tokGoto = GetCurrentToken() ;
		if (tokGoto.GetKeyword() == CCobolKeywordList.GOTO)
		{
			GetNext() ;
		}
		else if (tokGoto.GetKeyword() == CCobolKeywordList.GO)
		{
			CBaseToken tokTo = GetNext();
			if (tokTo.GetKeyword() == CCobolKeywordList.TO)
			{
				GetNext() ;
			}
		}
		else
		{
			Transcoder.logError(getLine(), "Expecting 'GOTO' keyword") ;
			return false ;
		}
		CGlobalEntityCounter.GetInstance().CountCobolVerb("GOTO") ;
		
		CBaseToken tokRef = GetCurrentToken() ;
		String csReference = "" ;
		while (tokRef.GetType() == CTokenType.IDENTIFIER || tokRef.GetType() == CTokenType.NUMBER)
		{
			csReference = tokRef.GetValue();
			m_arrReference.add(csReference);
			tokRef = GetNext() ;
		}
		
		CBaseToken tok = GetCurrentToken();
		while (tok.GetKeyword() == CCobolKeywordList.DEPENDING)
		{
			tok = GetNext() ;
			if (tok.GetKeyword() == CCobolKeywordList.ON)
			{
				tok = GetNext() ;
			}
			m_Dependence = ReadIdentifier();
		}
		return true ;
	}
	/* (non-Javadoc)
	 * @see parser.CLanguageElement#ExportCustom(org.w3c.dom.Document)
	 */
	protected Element ExportCustom(Document root)
	{
		if (m_arrReference.size() == 1)
		{
			Element eGoto = root.createElement("Goto") ;
			eGoto.setAttribute("Reference", m_arrReference.get(0)) ;
			return eGoto;
		}
		else
		{
			Element eGoto = root.createElement("Goto") ;
			for (int i=0; i<m_arrReference.size(); i++)
			{
				String cs = m_arrReference.get(i);
				Element e = root.createElement("Ref"+i);
				eGoto.appendChild(e);
				e.setAttribute("Reference", cs); 
			}
			Element e = root.createElement("DependingOn") ;
			eGoto.appendChild(e);
			m_Dependence.ExportTo(e, root);
			return eGoto;
		}
	}
	protected List<String> m_arrReference = new ArrayList<String>() ;
	protected CIdentifier m_Dependence = null ;
	/* (non-Javadoc)
	 * @see parser.CBaseElement#DoCustomSemanticAnalysis(semantic.CBaseSemanticEntity, semantic.CBaseSemanticEntityFactory)
	 */
	protected CBaseLanguageEntity DoCustomSemanticAnalysis(CBaseLanguageEntity parent, CBaseEntityFactory factory)
	{
		CBaseActionEntity e;
		if (m_Dependence == null)
		{
			e = factory.NewEntityGoto(getLine(), m_arrReference.get(0), parent.getSectionContainer()) ;
		}
		else
		{
			CDataEntity dep = m_Dependence.GetDataReference(getLine(), factory);
			e = factory.NewEntityGotoDepending(getLine(), m_arrReference, dep, parent.getSectionContainer());
		}
		parent.AddChild(e) ;
		return e;
	}
}
