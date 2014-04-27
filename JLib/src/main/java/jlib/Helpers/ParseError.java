/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.Helpers;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ParseError {
    public static String parseError(String comment, Throwable e) {
        String stackInfo;                     // Contenu de la pile d'appels.
        String errMessage;                    // Message d'erreur rendu par la fonction.
        try {

//************************* On r�cupere le message d'erreur *******************
            String s = e.getMessage();
            if (s == null)
                s = "...";
            int n = s.indexOf("##");

//***************** Si l'erreur vient de se produire **************************
            if (n < 0) {
//................ On r�cupere la pile d'appels ...............................
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                stackInfo = sw.toString();

//.................... On l'ins�re dans le message d'erreur ...................
                errMessage = comment + "[" + s + "]\n##Stack##\n---------\n" + stackInfo;
            }
//********* Si un appel ant�rieur a parseError a d�j� trait� l'erreur *********
            else {
                errMessage = comment + "->" + s;
            }

//************************ On rend le message modifi� *************************
            return errMessage;
        } catch (Exception ee) {
            return comment + ":" + e.getMessage() + "  Error in the error parser! (" + ee.getMessage() + ")";
        }
    }
}
