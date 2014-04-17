/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on Jul 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package parser.Cobol.elements;

import lexer.CBaseToken;
import lexer.Cobol.CCobolKeywordList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import parser.Cobol.CCobolElement;
import semantic.CBaseLanguageEntity;
import semantic.CBaseEntityFactory;
import utils.CGlobalEntityCounter;
import utils.Transcoder;

/**
 * @author U930CV
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CNextSentence extends CCobolElement
{
	/**
	 * @param line
	 */
	public CNextSentence(int line) {
		super(line);
	}
	/* (non-Javadoc)
	 * @see parser.CLanguageElement#Parse(lexer.CTokenList)
	 */
	protected boolean DoParsing(CFlag fFlag)
	{
		CBaseToken tokNext = GetCurrentToken() ;
		if (tokNext.GetKeyword() != CCobolKeywordList.NEXT)
		{
			Transcoder.logError(getLine(), "Expecting 'NEXT' keyword") ;
			return false ;
		}
		CGlobalEntityCounter.GetInstance().CountCobolVerb("NEXT_SENTENCE") ;
		CBaseToken tokSentence =GetNext();
		if (tokSentence.GetKeyword() != CCobolKeywordList.SENTENCE)
		{
			Transcoder.logError(getLine(), "Expecting 'SENTENCE' keyword") ;
			return false ;
		}
		GetNext() ;
		fFlag.Set();
		return true ;
	}
	/* (non-Javadoc)
	 * @see parser.CLanguageElement#ExportCustom(org.w3c.dom.Document)
	 */
	protected Element ExportCustom(Document root)
	{
		Element e = root.createElement("NextSentence") ;
		return e ;
	}
	/* (non-Javadoc)
	 * @see parser.CBaseElement#DoCustomSemanticAnalysis(semantic.CBaseSemanticEntity, semantic.CBaseSemanticEntityFactory)
	 */
	protected CBaseLanguageEntity DoCustomSemanticAnalysis(CBaseLanguageEntity parent, CBaseEntityFactory factory)
	{
		//m_Logger.warn("WARNING line "+getLine()+" : usage of NEXT SENTENCE is dangerous : check this code");
		return null ; // the NEXT SENTENCE statement does nothing else than telling the IF to go forward... 
	}
}
