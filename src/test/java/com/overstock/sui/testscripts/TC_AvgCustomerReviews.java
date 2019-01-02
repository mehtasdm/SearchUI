package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.AvgCustomerReviews;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Should return list of Left Nav Refinements --- Price | Brands | Avg. Customer Reviews
 * Using escape sequence for . in the css span#avg.-customer-reviews
 * 4 & up | 3 & up | 2 & up | 1 & up

 Expand Avg. Customer Reviews
 On Selection, the Avg. Customer Reviews refinement should stay open
 On Selection, the selected Avg. Customer Reviews should have tick mark, on hover the tick mark should display [x]
**/


public class TC_AvgCustomerReviews extends TestBase {

    AvgCustomerReviews avgCustomerReviews;
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    String testUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    int refineNum=8;
    boolean openAllRefinements=true;
    String avgCustomerText = "Customer Ratings";
    //
    @BeforeTest
    private void setUp() throws IOException {
        init();
        avgCustomerReviews = new AvgCustomerReviews(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    private void verifyDeliveryHeading() {
        if (!avgCustomerReviews.foundAvgCustomerReviewsText(avgCustomerText)) {
            logger.error("*** " + avgCustomerText + " Not Found ***");
        }
    }
    //
    @Test(priority = 1, enabled = true, dependsOnMethods = "verifyDeliveryHeading")
    private void expandAvgCustomerReviews() throws InterruptedException {
        if (!avgCustomerReviews.expandAvgCustomerReviews(avgCustomerText)) {
            logger.error("*** "+avgCustomerText+" Not Expanded ***");
        }
    }
    //
    @Test(priority = 2, enabled = true, dependsOnMethods = "expandAvgCustomerReviews")
    private void clickAvgCustomerReviews() throws InterruptedException {
        for (int i=0; i < avgCustomerReviews.countAvgCustomerReview(); i++) {
            avgCustomerReviews.clickAvgCustomerReviews(i);
            Thread.sleep(3000);
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.error("*** "+avgCustomerReviews.textAvgCustomerReviewList(i)+" was not clicked ****" );
            }

            avgCustomerReviews.clickAvgCustomerReviews(i);  // this should uncheck the selected avg. customer reviews
            Thread.sleep(3000);
            expandAvgCustomerReviews();                     // after the refinement is unselected, the Avg. Customer Reviews Refinement collapses and needs to be expanded
//            driver.navigate().back();
        }
    }
    //
}
