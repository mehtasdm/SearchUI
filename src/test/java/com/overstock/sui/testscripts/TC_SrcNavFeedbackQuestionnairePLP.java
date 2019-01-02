package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.SrcNavFeedbackQuestionnairePLP;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 5/3/2017.
 * SUI-155 - Create Search Navigation Feedback Questionnaire on PLP - DA
 * BtnYes
 * 1. Comment should accept multi-line input
 * 2. Comment should accept no input
 * 3. Thank you for your feedback! - message should be displayed
 * 4. After Submit - Yes button should be disabled
 * 5. After Submit - No button should be disabled
 * *
 * BtnNo
 * 1. Choose an Issue
 * 2. I need Customer Service
 * 3. Having trouble filtering/sorting
 * 4. Can’t find the desired product
 * 5. The product tile looks wrong
 * 6. Page is broken/won’t load
 * 7. Can you add a feature?
**/

public class TC_SrcNavFeedbackQuestionnairePLP extends TestBase {
    private String testUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    //
    SrcNavFeedbackQuestionnairePLP srcNavFeedbackQuestionnairePLP;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        srcNavFeedbackQuestionnairePLP = new SrcNavFeedbackQuestionnairePLP(driver);
        driver.get(testUrl);
    }
    //
//    @Test(priority = 0, enabled = true)
//    public void testYesBtnClicked() throws Exception {
//        srcNavFeedbackQuestionnairePLP.clickBtnYes();
//    }
    //
    @Test(priority = 1, enabled = true, dataProvider = "btnYes")
    public void testBtnYes(String aComment) throws NoSuchElementException, InterruptedException {
//        Thread.sleep(3000);
        testYesBtnEnabled(true);
        testNoBtnEnabled(true);
        testFeedbackTitle();
        testFooterMsg();
        srcNavFeedbackQuestionnairePLP.clickBtnYes();
//        Thread.sleep(500);
        srcNavFeedbackQuestionnairePLP.setComment(aComment);
        srcNavFeedbackQuestionnairePLP.clickBtnSubmit();
//        Thread.sleep(500);
        if (!srcNavFeedbackQuestionnairePLP.verifySuccessMsg()) {
            logger.info("*** Success Message not displayed for Null Comment ***");
        }
        testYesBtnEnabled(false);
        testNoBtnEnabled(false);
    }
    //
    @Test(priority = 2, enabled = true, dataProvider = "btnNo")
    public void testBtnNoWithComment(String issueDropDown) throws NoSuchElementException, InterruptedException {
        String strComment = "You can enter any text, but note that we are unable to respond directly to feedback";
        //
        driver.get(testUrl);
//        Thread.sleep(2000);
        testYesBtnEnabled(true);
        testNoBtnEnabled(true);
        testFeedbackTitle();
        testFooterMsg();
        srcNavFeedbackQuestionnairePLP.clickBtnNo();
//        Thread.sleep(100);
        testFeedbackIssueTitle1();
        srcNavFeedbackQuestionnairePLP.setFeedbackIssuesDropdown(issueDropDown);
//        Thread.sleep(100);
        if (issueDropDown.equals("I need Customer Service")) {
            if (!srcNavFeedbackQuestionnairePLP.verifyCCC()) {
                System.err.println("*** Contact Customer Care. text not displayed ***");
            }
            //
            srcNavFeedbackQuestionnairePLP.clickCCC();
//            Thread.sleep(500);
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.info("*** Contact Customer Care button not clicked ***");
            }
        } else {
            testFeedbackIssueTitle2();
            srcNavFeedbackQuestionnairePLP.clickBtnSubmit();  // without entering Comments
//            Thread.sleep(100);
            if (!srcNavFeedbackQuestionnairePLP.verifyErrorMsg()) {
                logger.info("*** It should not allow to Submit without entering Comments ***");
            }
            srcNavFeedbackQuestionnairePLP.setComment(strComment);
            srcNavFeedbackQuestionnairePLP.clickBtnSubmit();
//            Thread.sleep(500);
            if (!srcNavFeedbackQuestionnairePLP.verifySuccessMsg()) {
                logger.info("*** Success Message not displayed for Null Comment ***");
            }
            testYesBtnEnabled(false);
            testNoBtnEnabled(false);
        }
    }
    //
    private void testYesBtnEnabled(boolean bStatus) {
        if (srcNavFeedbackQuestionnairePLP.verifyBtnYesEnabled()!=bStatus) {
            logger.info("*** Yes Button should not be " + bStatus + " ***");
        }
    }
    //
    private void testNoBtnEnabled(boolean bStatus) {
        if (srcNavFeedbackQuestionnairePLP.verifyBtnNoEnabled() != bStatus) {
            logger.info("*** No Button should not be " + bStatus + " ***");
        }
    }
    //
    private void testFeedbackTitle() {
        if (!srcNavFeedbackQuestionnairePLP.verifyFeedbackTitle()) {
            logger.info("*** Feedback Title message - [Did you find what you were looking for?] not displayed ***");
        }
    }
    //
    private void testFeedbackIssueTitle1() {
        if (!srcNavFeedbackQuestionnairePLP.verifyFeedbackIssueTitle1()) {
            logger.info("*** Title1 message - [What best describes your issue?] not displayed ***");
        }
    }
    //
    private void testFeedbackIssueTitle2() {
        if (!srcNavFeedbackQuestionnairePLP.verifyFeedbackIssueTitle2()) {
            logger.info("*** Title2 message - [Please leave some comments about your experience.] not displayed ****");
        }
    }
    //
    private void testFooterMsg() {
        if (!srcNavFeedbackQuestionnairePLP.verifyFooterMsg()) {
            logger.info("*** Footer Message - [If you need further help or information please visit] not displayed ***");
        }
    }
    //
    @DataProvider(name = "btnYes")
    public Object[][] provideDataYes() {
        return new Object[][] {
            {""},
            {"You can enter any text, but note that we are unable to respond directly to feedback"}
        };
    }
    //
    @DataProvider(name = "btnNo")
    public Object[][] provideDataNo() {
        return new Object[][]{
//        "Choose an Issue",
            {"I need Customer Service"},
            {"Having trouble filtering/sorting"},
            {"Can’t find the desired product"}
//            {"The product tile looks wrong"},
//            {"Page is broken/won’t load"},
//            {"Can you add a feature?"}
        };
    }
    //
}