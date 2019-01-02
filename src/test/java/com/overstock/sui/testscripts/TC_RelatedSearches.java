package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.QuickView;
import com.overstock.sui.pagelibrary.RelatedReviews;
import com.overstock.sui.pagelibrary.RelatedSearches;
import com.overstock.sui.testbase.ExcelReader;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Related Searches Link at the Top above the Top Nav
 * Related Searches Taxonomy at the bottom
 * SUI-633
 * SUI-1162
 *
 * Search for pink | basketball | soccer
 * There can be a maximum of 8 Related Searches pills
 * For desktop they should be at the top below "Showing most popular results for your search. Click here  to show all results."
 * For Tablet & Mobile it should be at the bottom, below Questionnaire and "Showing most popular results for your search. Click here  to show all results."
 **/

public class TC_RelatedSearches extends TestBase {
    RelatedSearches relatedSearches;
    String testUrl = "https://www.overstock.com/search?keywords=basketball&taxonomy=sto5&ralt=sto1,sto33,sto43&TID=AR:TRUE&searchtype=Header";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());

    @BeforeTest
    public void setUp() throws IOException {
        init();
        relatedSearches = new RelatedSearches(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0)
    public void testRelatedSearchesHeading() throws InterruptedException {
        Thread.sleep(1000);
        if (! relatedSearches.getRelatedSearchHeading().contains("Related Searches:")) {
            logger.error("*** Related Searches missing ***");
        }
    }
    @Test(priority = 1)
    public void testRelatedSearchesLinks() {
        if (relatedSearches.lenghtRelatedSearches()==0) {
            logger.error("*** Related Searches Links not found ***");
        }
    }
    @Test(priority = 2)
    public void testRelatedSearchesLinkClicks() throws InterruptedException {
        int i=relatedSearches.lenghtRelatedSearches();
        for (int k=0; k< i; k++) {
            relatedSearches.clickRelatedSearchesLinks(k);
            Thread.sleep(1000);
            if (driver.getCurrentUrl()==testUrl) {
                logger.error("*** Related Searches Link " + k + " not clicked ***");
            }
            driver.get(testUrl);
            Thread.sleep(1000);
        }
    }
}
