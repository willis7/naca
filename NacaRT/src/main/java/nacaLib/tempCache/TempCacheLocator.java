/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 * 
 */
package nacaLib.tempCache;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: TempCacheLocator.java,v 1.3 2006/04/26 16:34:41 cvsadmin Exp $
 */
public class TempCacheLocator
{
	private static ThreadLocal<TempCache> mtls_tempCache = new ThreadLocal<TempCache>();

	public static TempCache setTempCache()
	{
		TempCache cache = TempCacheStack.pop();
		if(cache == null)
		{
			cache = new TempCache();	
		}		
		cache.resetStackProgram();
		mtls_tempCache.set(cache);
		return cache;
	}

	public static void relaseTempCache()
	{
		TempCache cache = getTLSTempCache();
		TempCacheStack.push(cache);
		mtls_tempCache.set(null);
	}
	
	public static TempCache getTLSTempCache()
	{
		return mtls_tempCache.get();
	}
}
