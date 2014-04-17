/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.batchPrgEnv;

import jlib.sql.DbConnectionManagerBase;
import jlib.xml.Tag;
import nacaLib.basePrgEnv.BaseProgramLoader;
import nacaLib.basePrgEnv.BaseSession;
import nacaLib.basePrgEnv.CBaseMapFieldLoader;
import nacaLib.exceptions.AbortSessionException;

public class BatchProgramLoader extends BaseProgramLoader
{
	public BatchProgramLoader(DbConnectionManagerBase connectionManager, Tag tagSequencerConfig)
	{
		super(connectionManager, tagSequencerConfig, true);
	}

	public void RunProgram(BaseSession appSession) throws AbortSessionException
	{
	}
	
	public static BatchProgramLoader GetProgramLoaderInstance() 
	{
		return (BatchProgramLoader)ms_Instance ;
	}
	
	public void doHelp(CBaseMapFieldLoader fieldLoader, BaseSession session)
	{
	}
}
