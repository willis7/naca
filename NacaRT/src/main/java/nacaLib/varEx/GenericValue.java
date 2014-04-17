/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/*
 * Created on 18 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nacaLib.varEx;


/**
 * @author U930DI
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class GenericValue
{
	GenericValue()
	{
	}
	
	abstract String getAsString();
	abstract String getAsRawString();
	abstract int getAsInt();
	abstract int getAsUnsignedInt();
	abstract Dec getAsDec();
	abstract Dec getAsUnsignedDec();
	abstract double getAsDouble();
}

