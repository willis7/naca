/*
 * JLib - Publicitas Java library.
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Publicitas SA.
 * Licensed under LGPL (LGPL-LICENSE.txt) license.
 */
package jlib.misc;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * <p>Envoi de mail</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Consultas</p>
 *
 * @author <a href=mailto:dbarman@consultas.ch>Barman Dominique</a>
 * @version 1.0
 */

public class Mail {
    private MimeMessage mimeMessage = null;

    private String m_csFrom = "";
    private Vector<String> m_arrToList = new Vector<String>(0);
    private Vector<String> m_arrCCList = new Vector<String>(0);
    private Vector<String> m_arrBCCList = new Vector<String>(0);

    /**
     * Contructeur du message � envoyer
     */
    public Mail(MailService mailService) {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailService.getSMTPServer());
        Session session = Session.getInstance(props);

        mimeMessage = new MimeMessage(session);
    }

    /**
     * Retourne le message
     */
    public MimeMessage getMessage() {
        return mimeMessage;
    }

    /**
     * Initialise le sujet du mail
     */
    public void setSubject(String subject) {
        try {
            mimeMessage.setSubject(subject);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initialise l'exp�diteur du mail
     */
    public void setFrom(String from) {
        m_csFrom = from;
    }

    /**
     * Initialise le texte du mail
     */
    public void setText(String text) {
        try {
            mimeMessage.setText(text);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Ajoute un destinataire
     */
    public void addTo(String to) {
        add(m_arrToList, to);
    }

    /**
     * Ajoute un destinataire en copie
     */
    public void addCc(String cc) {
        add(m_arrCCList, cc);
    }

    /**
     * Ajoute un destinataire en copie cach�e
     */
    public void addBcc(String bcc) {
        add(m_arrBCCList, bcc);
    }

    private void add(Vector<String> from, String mail) {
        String mailList[] = null;

        mailList = mail.split(";");
        for (int i = 0; i < mailList.length; i++) {
            from.add(mailList[i]);
        }
    }

    /**
     * Vide les destinataires
     */
    public void clearTo() {
        m_arrToList.clear();
    }

    /**
     * Vide les destinataires en copie
     */
    public void clearCc() {
        m_arrCCList.clear();
    }

    /**
     * Vide les destinataires en copie cach�e
     */
    public void clearBcc() {
        m_arrBCCList.clear();
    }

    /**
     * Envoie le mail
     */
    public void send() {
        try {
            mimeMessage.setFrom(new InternetAddress(m_csFrom));

            Enumeration entriesMail = m_arrToList.elements();
            while (entriesMail.hasMoreElements()) {
                mimeMessage.addRecipient(Message.RecipientType.TO,
                        new InternetAddress((String) entriesMail.nextElement()));
            }

            entriesMail = m_arrCCList.elements();
            while (entriesMail.hasMoreElements()) {
                mimeMessage.addRecipient(Message.RecipientType.CC,
                        new InternetAddress((String) entriesMail.nextElement()));
            }

            entriesMail = m_arrBCCList.elements();
            while (entriesMail.hasMoreElements()) {
                mimeMessage.addRecipient(Message.RecipientType.BCC,
                        new InternetAddress((String) entriesMail.nextElement()));
            }

            Transport.send(mimeMessage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}