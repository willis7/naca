/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.log.stdEvents;

import jlib.log.*;


/*
 * Created on 23 juin 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


/**
 * @author u930di
 * @deprecated Use {@link EventStart} instead.
 */
public class LogEventStartProcess extends LogEvent {
    public LogEventStartProcess(String csProduct) {
        super(LogEventType.Start, LogFlowStd.Any, LogLevel.Critical, csProduct);
    }

    public static LogEvent log(String csChannel) {
        return LogEventStartProcess.log(csChannel, null);
    }

    public static LogEvent log(String csChannel, String csProduct) {
        LogEventStartProcess event = new LogEventStartProcess(csProduct);
        Log.log(csChannel, event, "");
        return event;
    }

    public static LogEvent log(String csChannel, String csRunId, String csRuntimeId) {
        LogEventStartProcess event = new LogEventStartProcess(null);
        Log.log(csChannel, event, "", csRunId, csRuntimeId);
        return event;
    }

    public static LogEvent log(String csChannel, String csProduct, String csRunId, String csRuntimeId) {
        LogEventStartProcess event = new LogEventStartProcess(csProduct);
        Log.log(csChannel, event, "", csRunId, csRuntimeId);
        return event;
    }
}
