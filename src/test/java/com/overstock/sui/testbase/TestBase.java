package com.overstock.sui.testbase;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

/*
// import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
*/

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static Properties Repository = new Properties();
    // public static String excelFileName = "C:\\WorkspaceNew\\fusion-integration-tests\\src\\test\\resources\\urls-All pages.xlsx";
    public static String excelFileName = "C:\\WorkspaceNew\\fusion-integration-tests-Selenium-Java\\src\\test\\resources";
    public File fso;
    public FileInputStream fsoFI;
    public WebDriver driver;
    public String baseUrl;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    public void init() throws IOException {
        loadPropertiesFile();
        selectBrowser(Repository.getProperty("browser"));
        driver.get(Repository.getProperty("baseUrl"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().refresh();
    }
    //
    public void loadPropertiesFile() throws IOException {
        fso = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\overstock\\sui\\config\\config.properties");
        fsoFI = new FileInputStream(fso);
        Repository.load(fsoFI);
    }
    /*
    * This method initialize browser object
    * @param browser
    * @return browser driver
    */
    public WebDriver selectBrowser(String browser) {
        if (browser.equals("firefox") || browser.equals("FIREFOX") || browser.equals("FireFox") || browser.equals("Firefox")) {
            driver = new FirefoxDriver();
            return driver;
        } else if (browser.equals("chrome") || browser.equals("Chrome") || browser.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\ChromeDriver.exe");
            driver = new ChromeDriver();
            return driver;
        } else if (browser.equals("ie") || browser.equals("IE")) {
            System.setProperty("webdriver.", "C:\\Selenium\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            return driver;
        } else if (browser.equals("htmlunitdriver") || browser.equals("HtmlUnitDriver") || browser.equals("HTMLUNITDRIVER")) {
            driver = new HtmlUnitDriver();
            return driver;
        }
        return null;
    }
    //
    @BeforeTest
    public void loadlog4j() {
        String log4jConfPath = System.getProperty("user.dir")+"\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }
    //
    @AfterSuite
    public void tearsDown() throws EmailException {
//        sendReportByGMail();
//        logger.info("Sending suit email");
//        TestSuiteResults.sendSuiteResultEmail("Test Results for SUI Automation", "Sdm"+getDateTime());
    }
    //
    private  final static String getDateTime()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("PST"));
        return df.format(new Date());
    }

/*

    public static MimeMessage createMessage() {
        String toEmail = "smehta@overstock.com";
        String toTester1 = "smehta@overstock.com";
        String from = "smehta@overstock.com";
        String host = "c-qamail.overstock.com";
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.timeout", "120000");
        properties.put("mail.smtp.connectiontimeout", "120000");
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message;

        /*
        if (Bordello.get(Config.class).containsKey("com.overstock.xps.test.utils.EmailUtils.fromAddress")) {
            from = Bordello.get(Config.class).getString("com.overstock.xps.test.utils.EmailUtils.fromAddress");
        }

        if (Bordello.get(Config.class).containsKey("com.overstock.xps.test.utils.EmailUtils.toTester1")) {
            toTester1 = Bordello.get(Config.class).getString("com.overstock.xps.test.utils.EmailUtils.toTester1");
        }

        if (Bordello.get(Config.class).containsKey("com.overstock.xps.test.utils.EmailUtils.toTester2")) {
            toTester2 = Bordello.get(Config.class).getString("com.overstock.xps.test.utils.EmailUtils.toTester2");
        }
        */
/*
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
                LOGGER.info("Sending email failed. Trying again. " + e.getMessage());
            };
        }
        while (!sendSuccess && sendAttempts++ < 3);

        if (!sendSuccess) {
            LOGGER.error("Sending email failed. Giving up.");
        }
    }


    /*
    public class TestSuiteResults {
        try {
            MimeMessage message = createMessage();
            message.setSubject();
        }
    }

*/


/*

    public static void sendReportByGMail() throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("C:\\WorkspaceNew\\SearchUI2\\pom.xml");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("POM File");
        attachment.setName("SAM");

        // Create the email message
        Email email = new SimpleEmail();
//        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("sammygadi@gmail.com", "mypassword"));
        email.setSSLOnConnect(true);
        email.addTo("search-ui@overstock.com", "Search UI");
        email.setFrom("SMEHTA@overstock.com", "Me");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");
        // add the attachment
//        email.attach(attachment);
        // send the email
        email.send();
    }
*/
}
