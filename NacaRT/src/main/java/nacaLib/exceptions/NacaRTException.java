/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.exceptions;

public class NacaRTException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public NacaRTException()
	{
		super();
	}
	
	public NacaRTException(Throwable t)
	{
		super(t);
	}

	public NacaRTException(String message)
	{
		super(message);
	}
}
