package com.overstock.sui.customlistener;

import com.overstock.sui.testbase.TestBase;
import com.sun.jna.platform.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ListenerTest extends TestBase implements ITestListener {
    //
    public void onTestFailure(ITestResult arg0) {
        if(!arg0.isSuccess()) {

            String userDirectory = System.getProperty("user.dir");
//            String projLocation = "\\src\\test\\java\\com\\overstock\\sui\\screenshots\\";
            String projLocation = "\\screenshots\\";
            String failureImageFileName = userDirectory + projLocation + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-" + arg0.getMethod().getMethodName() + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                org.apache.commons.io.FileUtils.copyFile(scrFile, new File(failureImageFileName));
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Reporter.log("<a href=\"" + failureImageFileName + "\"><img src=\"file:///" + failureImageFileName + "\"alt=\"\"" + "height='100' width='100'/>" + "<br />");
            Reporter.setCurrentTestResult(null);
            Reporter.log(arg0.getName() + "---Test method failed\n");
            System.out.println(arg0.getName() + "---Test method failed\n");
        }
        Reporter.log("The name of the testcase failed is :" + arg0.getName());
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {}
    public void onTestStart(ITestResult arg0) {
        Reporter.log("Test started: " + arg0.getMethod().getMethodName() + "at: " + arg0.STARTED);
    }
    public void onTestSuccess(ITestResult arg0) {
        Reporter.log("Test Passed -> " + arg0.getName());
    }
    public void onTestSkipped(ITestResult arg0) {
        Reporter.log("Test Skipped -> " + arg0.getName());
    }
    public void onStart(ITestContext arg0) {}
    public void onFinish(ITestContext arg0) {}
    //
}
