/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 *
 */
package jlib.jslibComp;


import java.io.Serializable;

/**
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id$
 */
public class TextItem implements Serializable {
    public String codeId;
    public String valText;

    public TextItem(String csId, String csValue) {
        codeId = csId;
        valText = csValue;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getValText() {
        return valText;
    }
}
