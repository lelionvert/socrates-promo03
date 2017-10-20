package com.lacombe.promo3.communication.repository;

import com.lacombe.promo3.communication.model.EmailMessage;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import java.util.Properties;

public class SMTPEmailSender implements EmailSender {

    Session session;

    public SMTPEmailSender(Properties properties) {
        final String username = "lacombe.smtp@gmail.com";
        final String password = "LaCombePromo03";

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    @Override
    public void send(EmailMessage emailMessage) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set emailMessage headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(emailMessage.getSender().getEmailAddress(), "Houssam"));

            msg.setSubject(emailMessage.getObject(), "UTF-8");

            msg.setText(emailMessage.getBody(), "UTF-8");

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    emailMessage.getRecipient().getEmailAddress(), false));
            System.out.println("EmailMessage is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
