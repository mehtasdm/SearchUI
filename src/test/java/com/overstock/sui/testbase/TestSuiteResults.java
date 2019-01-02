package com.overstock.sui.testbase;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.overstock.sui.testbase.EmailUtils;

public class TestSuiteResults {

  public static void sendSuiteResultEmail(String emailSubject, String outputFilename) {
    try {
        // Create a default MimeMessage object.
        MimeMessage message = EmailUtils.createMessage();

        message.setSubject(emailSubject);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        BufferedWriter br = new BufferedWriter(new FileWriter(outputFilename));
//        String result = SuiteManager.getCsvString();
//        br.write(result);

//        messageBodyPart.setContent(SuiteManager.getEmailBody(emailSubject),"text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(outputFilename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(outputFilename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        EmailUtils.sendMessage(message);
    }
    catch (Exception mex) {
        mex.printStackTrace();
    }
  }
}
