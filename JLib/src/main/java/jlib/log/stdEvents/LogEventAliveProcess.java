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
 * @deprecated Use the {@link EventProgress}, instead.
 */
public class LogEventAliveProcess extends LogEvent {
    public LogEventAliveProcess(String csProduct) {
        super(LogEventType.Remark, LogFlowStd.Monitoring, LogLevel.Normal);
    }

    public static LogEvent log(String csChannel) {
        return LogEventAliveProcess.log(null);
    }

    public static LogEvent log(String csChannel, String csProduct) {
        LogEventAliveProcess event = new LogEventAliveProcess(csProduct);
        Log.log(csChannel, event, "");
        return event;
    }
}
