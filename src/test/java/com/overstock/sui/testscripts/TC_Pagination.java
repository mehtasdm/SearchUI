package com.overstock.sui.testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.overstock.sui.pagelibrary.Pagination;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * Created by smehta on 6/20/2017.
 * Move Next Page
 * Move Previous Page
 * Browser Back Button
 * Move Page number wise
 */
public class TC_Pagination extends TestBase {
    private String testUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    //
    Pagination pagination;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        pagination = new Pagination(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    public void verifyPageLoad() throws NoSuchElementException {
//        try {
            JavascriptExecutor moveto = ((JavascriptExecutor) driver);
            moveto.executeScript("window.scrollBy(0,6000)", "");
//            if (driver.findElement(By.xpath("//*[@id=\'sn-wrapper\']/div/div/div[2]/div/div[3]/div[1]/div/div[1]")).isEnabled()){
//                logger.info("Condition unmatched");
//                }
            if (!previousPageDisabledOnPageLoad()) {
                logger.info("*** Previous Page should not be Enabled on Page Load ***");
            } else {
                logger.info("Pass - Previous Page is Disabled on Page Load");
            }
            if (!nextPageEnabled()) {
                logger.info("*** Next Page should not be Disabled on Page Load ***");
            } else {
                logger.info("Pass - Next Page Enabled on Page Load");
            }
            if (!pageIsActive(1)) {
                logger.info("*** Page 1 is not Active on Page Load ***");
            } else {
                logger.info("Pass - Page 1 is Active on Page Load");
            }
            if (pageIsActive(2)) {
                logger.info("*** Page 2 should not be Active on Page Load ***");
            } else {
                logger.info("Pass - Page 2 is not Active on Page Load");
            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
    }
    //
    @Test(priority = 1, enabled = true)
    public void clickNextPage() throws NoSuchElementException, InterruptedException {
        Thread.sleep(3000);
//        try {
            pagination.clickPageNext();
            Thread.sleep(3000);
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.info("*** Next Page not Clicked ***");
            }
            if (!driver.getCurrentUrl().contains("page=2")) {
                logger.info("*** Next Page not Clicked URL missing page= ***");
            }
            if (!pageIsActive(2)) {
                logger.info("*** Page 2 is not Active on Next Page ***");
            }
            if (!nextPageEnabled()) {
                logger.info("*** Next Page should be Enabled on Next Page");
            }
            if (pageIsActive(1)) {
                logger.info("*** Page 1 should not be Active on Next Page ***");
            }
            if (!previousPageEnabled()) {
                logger.info("*** Previous Page is not Enabled on Next Page ***");
            }
//        } catch (Exception e){
//            logger.info(e.getMessage());
//        }
    }
    //
    @Test(priority = 2, enabled = true)
    public void clickPreviousPage() throws NoSuchElementException, InterruptedException {
        Thread.sleep(3000);
//        try {
            pagination.clickPagePrevious();
            Thread.sleep(3000);
            //
            if (!driver.getCurrentUrl().equals(testUrl)) {
                logger.info("*** Previous Page not Clicked ***");
            }
            if (driver.getCurrentUrl().contains("page=")) {
                logger.info("*** Previous Page not Clicked URL has page= ***");
            }
            if (!pageIsActive(1)) {
                logger.info("*** Page 1 not Active on First Page ***");
            }
            if (previousPageEnabled()) {
                logger.info("*** Previous Page is not Disabled on First Page ***");
            }
            if (pageIsActive(2)) {
                logger.info("*** Page 2 should not be Active on First Page ***");
            }
            if (!nextPageEnabled()) {
                logger.info("*** Next Page should be Enabled on First Page ***");
            }
//        } catch (Exception e){
//            System.err.println(e.getMessage());
//        }
    }
    //
    public boolean previousPageDisabledOnPageLoad() throws NoSuchElementException {
        try {
            return (pagination.pagePreviousOnPageLoadDisabled());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    //
    public boolean previousPageEnabled() throws NoSuchElementException {
        try {
            return (pagination.pagePreviousEnabled());
            }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    //
    public boolean nextPageEnabled() throws NoSuchElementException {
        try {
            return (pagination.pageNextEnabled());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    //
    public boolean pageIsActive(int pageNumber) throws NoSuchElementException {
        try {
            return pagination.isPageActive(pageNumber);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    //
    /*
    public boolean page2Active() throws NoSuchElementException {
        try {
            return pagination.page2IsActive();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    */
    //
}
