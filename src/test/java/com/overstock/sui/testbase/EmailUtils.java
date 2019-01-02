package com.overstock.sui.testbase;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailUtils {

  private static final Logger logger = Logger.getLogger(EmailUtils.class);

  public static MimeMessage createMessage() {
      String toXpay = "smehta@overstock.com";
      String toTester1 = "smehta@overstock.com";
      //String toTester2 = "kmodi@overstock.com";
      String from = "smehta@overstock.com";
      String host = "c-qamail.overstock.com";
      Properties properties = new Properties();
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.timeout", "120000");
      properties.put("mail.smtp.connectiontimeout", "120000");
      Session session = Session.getDefaultInstance(properties);
      MimeMessage message;

      try {
          message = new MimeMessage(session);
          message.setFrom(new InternetAddress(from));
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(toTester1));
      }
      catch (Exception e) {
          throw new RuntimeException(e.getMessage());
      }
      return message;
  }

  public static void sendMessage(MimeMessage message) {
      boolean sendSuccess = false;
      int sendAttempts = 0;
      do {
          try {
              Transport.send(message);
              sendSuccess = true;
          }
          catch (Exception e) {
              logger.info("Sending email failed. Trying again. " + e.getMessage());
          };
      }
      while (!sendSuccess && sendAttempts++ < 3);
          if (!sendSuccess) {
              logger.error("Sending email failed. Giving up.");
          }
  }
}
