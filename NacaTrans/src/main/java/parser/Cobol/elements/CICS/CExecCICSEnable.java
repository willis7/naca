/*
 * NacaRTTests - Naca Tests for NacaRT support.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under GPL (GPL-LICENSE.txt) license.
 */
/*
 * Created on 15 sept. 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package parser.Cobol.elements.CICS;

import lexer.CBaseToken;
import lexer.Cobol.CCobolKeywordList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import parser.Cobol.CCobolElement;
import semantic.CBaseEntityFactory;
import semantic.CBaseLanguageEntity;
import utils.Transcoder;

/**
 * @author sly
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CExecCICSEnable extends CCobolElement
{

	/**
	 * @param line
	 */
	public CExecCICSEnable(int line)
	{
		super(line);
	}
	protected CBaseLanguageEntity DoCustomSemanticAnalysis(CBaseLanguageEntity parent, CBaseEntityFactory factory)
	{
		Transcoder.logError(getLine(), "No Semantic Analysis for EXEC CICS ENABLE") ;
		return null;
	}
	protected boolean DoParsing()
	{
		CBaseToken tok = GetCurrentToken() ;
		//if (tok.GetKeyword() == CCobolKeywordList.)
		{
			tok = GetNext();
		}

		String cs = "" ;
		tok = GetCurrentToken() ;
		while (tok.GetKeyword() != CCobolKeywordList.END_EXEC)
		{
			cs += tok.GetDisplay() + " " ;
			tok = GetNext() ;
		}		

		if (tok.GetKeyword() != CCobolKeywordList.END_EXEC)
		{
			Transcoder.logError(getLine(), "Error while parsing EXEC CICS ENABLE");
			return false ;
		}
		StepNext();
		return true ;
	}
	protected Element ExportCustom(Document root)
	{
		//Transcoder.logError("No Export for EXEC CICS ENABLE") ;
		Element e = root.createElement("ExecCICSEnable") ;
		return e;
	}

}
