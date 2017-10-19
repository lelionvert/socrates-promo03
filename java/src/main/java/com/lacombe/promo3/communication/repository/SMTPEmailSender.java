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

    public SMTPEmailSender() {
        final String username = "lacombe.smtp@gmail.com";
        final String password = "LaCombePromo03";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
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
