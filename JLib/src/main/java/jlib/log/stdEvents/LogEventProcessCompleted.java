/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.log.stdEvents;

import jlib.log.*;

/**
 * @deprecated Use {@link EventReport} instead.
 */
public class LogEventProcessCompleted extends LogEvent {

    public LogEventProcessCompleted(LogEventType logEventType, LogFlow logFlow, LogLevel logLevel, String csProduct) {
        super(LogEventType.Completed, LogFlowStd.Any, LogLevel.Critical, csProduct);
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

}
