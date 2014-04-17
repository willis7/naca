/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.batchOOApi;

import nacaLib.varEx.FileDescriptor;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id$
 */
public abstract class BaseVariableLengthRecord extends BaseRecord  
{
	protected BaseVariableLengthRecord(FileDescriptor file)
	{
		super(file);
	}
	
	public abstract void fillRW();
}
