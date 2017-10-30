package Imap;

import Controller.SendMailBodyController;
import View.SendMailBodyView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Kamil on 22.10.2017.
 */
public class MailSender {

    public void send()
    {

        String to = "kmlszelg98@gmail.com";
        String from = "szelagkamil0@gmail.com";
        final String username = "szelagkamil0@gmail.com";
        final String password = "kmlszelg";


        String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Testing Subject");

            message.setText("Hello, this is sample for to check send " +
                    "email using JavaMailAPI ");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
