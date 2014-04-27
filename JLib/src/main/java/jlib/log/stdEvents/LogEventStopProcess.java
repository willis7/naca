/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.log.stdEvents;

import jlib.log.*;

/**
 * @author PJD
 * @deprecated Use {@link EventFinish} instead.
 */
public class LogEventStopProcess extends LogEvent {
    public LogEventStopProcess(String csProduct) {
        super(LogEventType.Stop, LogFlowStd.Any, LogLevel.Critical, csProduct);
    }

    public static LogEvent log(String csChannel) {
        return LogEventStopProcess.log(csChannel, null);
    }

    public static LogEvent log(String csChannel, String csProduct) {
        LogEventStopProcess event = new LogEventStopProcess(csProduct);
        Log.log(csChannel, event, "");
        return event;
    }

    public static LogEvent log(String csChannel, String csProduct, String csRunId, String csRuntimeId) {
        LogEventStopProcess event = new LogEventStopProcess(csProduct);
        Log.log(csChannel, event, "", csRunId, csRuntimeId);
        return event;
    }
}
