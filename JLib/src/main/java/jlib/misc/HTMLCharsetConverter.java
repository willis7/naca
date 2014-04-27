/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 *
 */
package jlib.misc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: HTMLCharsetConverter.java,v 1.3 2008/01/11 13:15:39 u930bm Exp $
 */
public class HTMLCharsetConverter {
    private static ArrayList<String> ms_arrCodes = null;
    private static Hashtable<String, Character> ms_hashCodes = null;

    public synchronized static void initOnce() {
        if (ms_arrCodes != null)    // init only once
            return;
        ms_arrCodes = new ArrayList<String>();
        ms_hashCodes = new Hashtable<String, Character>();

        for (int n = 0; n < 256; n++) {
            String cs = "&#" + n + ";";
            ms_arrCodes.add(cs);
            ms_hashCodes.put(cs, (char) n);
        }

        set(34, "&quot;"); //     "
        set(38, "&amp;");    //
        set(39, "&rsquo;"); //     '
        set(60, "&lt;");    //       <
        set(62, "&gt;");    //       >
        set(160, "&nbsp;"); //
        set(161, "&iexcl;"); //	�
        set(162, "&cent;");  //    �
        set(163, "&pound;"); //	�
        set(164, "&curren;"); //  �
        set(165, "&yen;");        //	�
        set(166, "&brvbar;");    //  �
        set(167, "&sect;");    //�
        set(168, "&uml;");        //  �
        set(169, "&copy;");    //�
        set(170, "&ordf;");    //    �
        set(171, "&laquo;");    //�
        set(172, "&not;");        //     �
        set(173, "&shy;");        //�
        set(174, "&reg;");        //     �
        set(175, "&macr;");    //�
        set(176, "&deg;");        //     �
        set(177, "&plusmn;"); //�
        set(178, "&sup2;"); //    �
        set(179, "&sup3;"); //�
        set(180, "&acute;"); //   �
        set(181, "&micro;"); //�
        set(182, "&para;"); //    �
        set(183, "&middot;"); //�
        set(184, "&cedil;"); //   �
        set(185, "&sup1;"); //�
        set(186, "&ordm;"); //    �
        set(187, "&raquo;"); //�
        set(188, "&frac14;"); //  �
        set(189, "&frac12;"); //�
        set(190, "&frac34;"); //  �
        set(191, "&iquest;"); //�
        set(192, "&Agrave;"); //  �
        set(193, "&Aacute;"); //�
        set(194, "&Acirc;"); //   �
        set(195, "&Atilde;"); //�
        set(196, "&Auml;"); //    �
        set(197, "&Aring;"); //�
        set(198, "&AElig;"); //   �
        set(199, "&Ccedil;"); //�
        set(200, "&Egrave;"); //  �
        set(201, "&Eacute;"); //�
        set(202, "&Ecirc;"); //   �
        set(203, "&Euml;"); //�
        set(204, "&Igrave;"); //  �
        set(205, "&Iacute;"); //�
        set(206, "&Icirc;"); //   �
        set(207, "&Iuml;"); //�
        set(208, "&ETH;"); //     �
        set(209, "&Ntilde;"); //�
        set(210, "&Ograve;"); //  �
        set(211, "&Oacute;"); //�
        set(212, "&Ocirc;"); //   �
        set(213, "&Otilde;"); //�
        set(214, "&Ouml;"); //    �
        set(215, "&times;"); //�
        set(216, "&Oslash;"); //  �
        set(217, "&Ugrave;"); //�
        set(218, "&Uacute;"); //  �
        set(219, "&Ucirc;"); //�
        set(220, "&Uuml;"); //    �
        set(221, "&Yacute;"); //�
        set(222, "&THORN;"); //   �
        set(223, "&szlig;"); //�
        set(224, "&agrave;"); //  �
        set(225, "&aacute;"); //�
        set(226, "&acirc;"); //   �
        set(227, "&atilde;"); //�
        set(228, "&auml;"); //    �
        set(229, "&aring;"); //�
        set(230, "&aelig;"); //   �
        set(231, "&ccedil;"); //�
        set(232, "&egrave;"); //  �
        set(233, "&eacute;"); //�
        set(234, "&ecirc;"); //   �
        set(235, "&euml;"); //�
        set(236, "&igrave;"); //  �
        set(237, "&iacute;"); //�
        set(238, "&icirc;"); //   �
        set(239, "&iuml;"); //�
        set(240, "&eth;"); //     �
        set(241, "&ntilde;"); //�
        set(242, "&ograve;"); //  �
        set(243, "&oacute;"); //�
        set(244, "&ocirc;"); //   �
        set(245, "&otilde;"); //�
        set(246, "&ouml;"); //    �
        set(247, "&divide;"); //�
        set(248, "&oslash;"); //  �
        set(249, "&ugrave;"); //�
        set(250, "&uacute;"); //  �
        set(251, "&ucirc;"); //�
        set(252, "&uuml;"); //    �
        set(253, "&yacute;"); //�
        set(254, "&thorn;"); //   �
        set(255, "&yuml;"); //�
    }

    private static void set(int nAsciiCode, String csHTMLCode) {
        ms_arrCodes.set(nAsciiCode, csHTMLCode);
        ms_hashCodes.put(csHTMLCode, (char) nAsciiCode);
    }

    static String getEncodedString(char c) {
        int nAsciiCode = (int) c;
        if (nAsciiCode >= 0 && nAsciiCode < 256) {
            String csHTMLCode = ms_arrCodes.get(nAsciiCode);
            return csHTMLCode;
        }
        return "" + c;
    }

    static char getDecodedChar(String csHTMLValue) {
        return ms_hashCodes.get(csHTMLValue);
    }

    static String encodeIntoCustomString(String csHTMLValue) {
        Character c = ms_hashCodes.get(csHTMLValue);
        if (c != null) {
            csHTMLValue = "[" + csHTMLValue.substring(1).toUpperCase() + "]";
        }
        return csHTMLValue;
    }

    static String decodeFromCustomString(String csHTMLValue) {
        if (csHTMLValue.startsWith("[") && csHTMLValue.endsWith("]")) {
            csHTMLValue = csHTMLValue.substring(1, csHTMLValue.length() - 1);
            csHTMLValue = csHTMLValue.toLowerCase();
            csHTMLValue = "&" + csHTMLValue;
        }
        return csHTMLValue;
    }

    public static StringBuilder encodeIntoCustomString(StringBuilder sb) {
        initOnce();
        Set<String> arr = ms_hashCodes.keySet();
        Iterator<String> iter = arr.iterator();
        while (iter.hasNext()) {
            String csHTMLValue = iter.next();
            if (csHTMLValue.length() > 1) {
                String csEncodedHTMLValue = encodeIntoCustomString(csHTMLValue);
                StringUtil.replace(sb, csHTMLValue, csEncodedHTMLValue);
            }
        }
        // Encode also chars encoded with numeric value (&#215; -> [#215])
        int nStart = sb.indexOf("&#");
        while (nStart >= 0) {
            int nEnd = sb.indexOf(";", nStart);
            String csNumVal = sb.substring(nStart + 2, nEnd);
            if (StringUtil.isAllDigits(csNumVal)) {
                csNumVal = "[#" + csNumVal + "]";
                String csLeft = sb.substring(0, nStart);
                String csRight = sb.substring(nEnd + 1);
                sb.setLength(0);
                sb.append(csLeft);
                sb.append(csNumVal);
                sb.append(csRight);
            }

            nStart = sb.indexOf("&#", nEnd);
        }
        return sb;
    }

    public static StringBuilder decodeFromCustomString(StringBuilder sb) {
        initOnce();
        Set<String> arr = ms_hashCodes.keySet();
        Iterator<String> iter = arr.iterator();
        while (iter.hasNext()) {
            String csHTMLValue = iter.next();
            if (csHTMLValue.length() > 1) {
                String csEncodedHTMLValue = encodeIntoCustomString(csHTMLValue);
                StringUtil.replace(sb, csEncodedHTMLValue, csHTMLValue);
            }
        }
        // Decode also chars encoded with numeric value ([#215] -> &#215;)
        int nStart = sb.indexOf("[#");
        while (nStart >= 0) {
            int nEnd = sb.indexOf("]", nStart);
            String csNumVal = sb.substring(nStart + 2, nEnd);
            if (StringUtil.isAllDigits(csNumVal)) {
                csNumVal = "&#" + csNumVal + ";";
                String csLeft = sb.substring(0, nStart);
                String csRight = sb.substring(nEnd + 1);
                sb.setLength(0);
                sb.append(csLeft);
                sb.append(csNumVal);
                sb.append(csRight);
            }

            nStart = sb.indexOf("[#", nEnd);
        }

        return sb;
    }
}
