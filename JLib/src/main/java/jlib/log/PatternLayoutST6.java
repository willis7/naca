/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.log;


/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import jlib.misc.DateUtil;


/**
 * @author U930DI
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PatternLayoutST6 extends LogPatternLayout {
    public PatternLayoutST6() {
        super();
    }

    String getMessage(LogParams logParams) {
        return format(logParams, 0);
    }

    String format(LogParams logParams, int n) {
        if (n == 0) {
            String csType = "4";    // Rem
            LogEventType logEventType = logParams.getLogEventType();
            if (logEventType == LogEventType.Error)
                csType = "0";    // Error
            else if (logEventType == LogEventType.Warning)
                csType = "1";    // Waring

            String csMessage = "";
            String csFile = "";
            String csClass = "";
            String csLine = "";
            String csMethodName = "";
            int nCode = 0;

            csMessage = logParams.toString();

            StackTraceElement stackElem = logParams.m_caller;
            if (stackElem != null) {
                csFile = stackElem.getFileName();
                csClass = stackElem.getClassName();
                int nLine = stackElem.getLineNumber();
                csLine = Integer.toString(nLine);
                csMethodName = stackElem.getMethodName();
            }

            String csDate = DateUtil.getCurrentDisplayableDateTime();

            String csOut = csType + "," + nCode + ",�" + logParams.getThreadName() + "�," + (int) logParams.getStartTime() + ",�" + csDate + "�,�" + csFile + "�," + csLine + ",�" + csClass + "::" + csMethodName + "�,�" + "Log Session" + "�,�" + csMessage + "�,�" + csMessage + "�\n";
            return csOut;
        }
        return null;
    }

    int getNbLoop(LogParams logParams) {
        return 1;
    }
}

