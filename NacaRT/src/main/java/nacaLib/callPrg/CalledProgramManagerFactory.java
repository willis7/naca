/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package nacaLib.callPrg;

import nacaLib.basePrgEnv.BaseProgram;
import nacaLib.basePrgEnv.BaseProgramManager;
import nacaLib.basePrgEnv.BaseProgramManagerFactory;
import nacaLib.programPool.SharedProgramInstanceData;

public class CalledProgramManagerFactory extends BaseProgramManagerFactory
{
	public BaseProgramManager createProgramManager(BaseProgram prg, SharedProgramInstanceData sharedProgramInstanceData, boolean bInheritedSharedProgramInstanceData)
	{
		return new CalledProgramManager(prg, sharedProgramInstanceData, bInheritedSharedProgramInstanceData);
	}
}