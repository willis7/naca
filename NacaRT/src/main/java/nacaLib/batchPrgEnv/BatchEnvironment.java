/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.batchPrgEnv;

import idea.manager.CESMManager;
import jlib.sql.DbConnectionManagerBase;
import nacaLib.basePrgEnv.BaseCESMManager;
import nacaLib.basePrgEnv.BaseEnvironment;
import nacaLib.basePrgEnv.BaseResourceManager;
import nacaLib.basePrgEnv.BaseSession;

public class BatchEnvironment extends BaseEnvironment
{
	public BatchEnvironment(BatchSession batchSession, DbConnectionManagerBase connectionManager, BaseResourceManager baseResourceManager)
	{	
		super(batchSession, connectionManager, baseResourceManager);
	}
	
	public BaseCESMManager createCESMManager()
	{
		return new CESMManager(this);
	}
	
	public BaseSession getSession()
	{
		return null;
	}
	
	public void breakCurrentSessionIfTimeout()
	{
	}
	
}
