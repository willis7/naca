/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.exceptions;

import nacaLib.program.Paragraph;

public class CGotoOtherSectionParagraphException extends NacaRTException
{
	private static final long serialVersionUID = 1L;
	public Paragraph m_Paragraph = null;

	public CGotoOtherSectionParagraphException(Paragraph paragraph)
	{
		super();
		m_Paragraph = paragraph;
	}
}
