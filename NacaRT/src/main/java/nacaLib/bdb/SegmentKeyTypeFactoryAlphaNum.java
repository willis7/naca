/*
 * NacaRT - Naca RunTime for Java Transcoded Cobol programs.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
/**
 * 
 */
package nacaLib.bdb;

/**
 *
 * @author Pierre-Jean Ditscheid, Consultas SA
 * @version $Id: SegmentKeyTypeFactoryAlphaNum.java,v 1.1 2006/05/01 12:38:51 cvsadmin Exp $
 */
public class SegmentKeyTypeFactoryAlphaNum extends BtreeSegmentKeyTypeFactory
{
	BtreeKeySegment make(int nKeyPositionInData, int nKeyPositionInKey, int nBufferLength, boolean bAscending)
	{
		return new BtreeKeySegmentAlphaNum(nKeyPositionInData, nKeyPositionInKey, nBufferLength, bAscending);
	}
}
