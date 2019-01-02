package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.BackToTop;
import com.overstock.sui.testbase.FusionResultPageUtils;
import com.overstock.sui.testbase.TestBase;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 *  Verify scroll down
 *  Verify scroll up
 *  Verify click Back To Top
 *  Verify Back To Top not displayed at the top of the page (no scroll down)
 *  Verify on click of Back To Top, it is at the top of the page
*

*/
//
public class TC_BackToTop extends TestBase {
    BackToTop backToTop;
    FusionResultPageUtils fusionResultPageUtils;
    String testUrl = "https://www.overstock.com/Jewelry-Watches/Rings/2360/cat.html";
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        backToTop = new BackToTop(driver);
        fusionResultPageUtils = new FusionResultPageUtils(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    public void verifyBackToTopNotDisplayedOnPageLoad() throws NoSuchElementException {
        if (backToTop.backToTopVisible()) {
            logger.info("*** Back To Top should not be displayed on Page Load ***");
        }
    }
    //
    @Test(priority = 1, enabled = true)
    public void verifyBackToTopDisplayedOnScrollDown() throws NoSuchElementException, InterruptedException {
        fusionResultPageUtils.mouseScroll(4500);    // Scroll Down
        if (backToTop.backToTopVisible()) {
            logger.info("*** Back To Top should not be displayed on Scroll Down ***");
        }
    }
    //
    @Test(priority = 2, enabled = true)
    public void verifyBackToTopDisplayedOnScrollUp() throws NoSuchElementException, InterruptedException {
        fusionResultPageUtils.mouseScroll(4500);    // Scroll Down
        fusionResultPageUtils.mouseScroll(-1000);    // Scroll Up
        backToTop.setBackToTopToBeVisible();
        if (!backToTop.backToTopVisible()) {
            logger.info("*** Back To Top should be displayed on Scroll Up ***");
        }
    }
    //
    @Test(priority = 3, enabled = true)
    public void clickBackToTopLink() throws NoSuchElementException, InterruptedException {
        fusionResultPageUtils.mouseScroll(4500);    // Scroll Down
        fusionResultPageUtils.mouseScroll(-1500);    // Scroll Up
        backToTop.setBackToTopToBeVisible();
        backToTop.clickBackToTop();
    }
    //
    @Test(priority = 3, enabled = true, dependsOnMethods = {"clickBackToTopLink"})
    public void verifyBackToTopLinkClicked() throws NoSuchElementException, InterruptedException {
        if (!fusionResultPageUtils.pageTop()) {
            logger.info("*** Back To Top did not push to Top of Page");
        }
    }
    //
}
