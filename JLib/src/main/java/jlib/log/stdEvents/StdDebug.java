/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
// D:\dev\AdSpiderDriving\Logging\StdDebug.java
// STModuleGen generated JLib log class

package jlib.log.stdEvents;

import jlib.log.Log;
import jlib.log.LogEvent;
import jlib.log.LogEventType;
import jlib.log.LogFlowStd;
import jlib.log.LogLevel;

/**
 * @deprecated Use {@link EventRemark} instead. 
 * @author PJD
 */
public class StdDebug extends LogEvent
{
	public StdDebug(String csProduct)
	{
		super(LogEventType.Remark, LogFlowStd.Trace, LogLevel.Debug, csProduct);
	}
	
	public static LogEvent log(String csChannel, String csContext, String csMessage)
	{
		return StdDebug.log(csChannel, null, csContext, csMessage);
	}

	public static LogEvent log(String csChannel, String csProduct, String csContext, String csMessage)
	{
		StdDebug event = new StdDebug(csProduct);
		event.fillMember("Context", csContext) ;
		Log.log(csChannel, event, csMessage);
		return event;
	}
}
