/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/*
 * Created on 7 juil. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jlib.log;

/**
 * @author PJD
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class LogEventWarning extends LogEvent {
    LogEventWarning() {
        super(LogEventType.Warning, LogFlowStd.System, LogLevel.Normal);
    }

    public static LogEvent info() {
        LogEventWarning event = new LogEventWarning();
        return event;
    }
}

