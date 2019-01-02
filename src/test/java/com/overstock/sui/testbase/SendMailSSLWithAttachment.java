package com.overstock.sui.testbase;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;

public class SendMailSSLWithAttachment {

    public static boolean sendReportEMail() {
        Properties properties = new Properties();
/*

        // this will set host of server- you can change based on your requirement
        properties.put("mail.smtp.host", "smtp.gmail.com");
        // set the port of socket factory
        properties.put("mail.smtp.socketFactory.port", "465");
        // set socket factory
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        // set the authentication to true
        properties.put("mail.smtp.auth", "true");
        // set the port of SMTP server
        properties.put("mail.smtp.port", "465");
        //
*/
        String toMail = "search-ui@overstock.com";
        String toTester1 = "smehta@overstock.com";
        String from = "smehta@overstock.com";
        String host = "c-qamail.overstock.com";

        // This will handle the complete authentication
/*
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("smehta@overstock.com", "mypassword");
                }
            });
*/
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toTester1));

            // Set the recipient address
//            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("search-ui@overstock.com"));
            // Add the subject link
            message.setSubject("Testing Subject");
            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();
            // Set the body of email
            messageBodyPart1.setText("This is message body");
            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            // Mention the file which you want to send
            String filename = "G:\\a.xlsx";
            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);
            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));
            // set the file
            messageBodyPart2.setFileName(filename);
            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();
            // add body part 1
            multipart.addBodyPart(messageBodyPart2);
            // add body part 2
            multipart.addBodyPart(messageBodyPart1);
            // set the content
            message.setContent(multipart);
            // finally send the email
            Transport.send(message);
//            System.out.println("=====Email Sent=====");
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }
}
