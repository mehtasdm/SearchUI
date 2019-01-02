package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.HeaderKeywordSearch;
import com.overstock.sui.pagelibrary.NoResultsPage;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

/**
 * Created by smehta on 5/5/2017.
 * Search for product which does not exists in overstock database
 * Verify "Your search: keyword returned no results."
 * Verfiy it displays carousel of Recommended for You products
 * Verify it displays carousel of Overstock.com Top Sellers
 * Left Nav should not be displayed
 */
public class TC_NoResultsPage extends TestBase{
    NoResultsPage noResultsPage;
    HeaderKeywordSearch headerKeywordSearch;
    String baseUrl = "https://wwww.overstock.com";
    String searchKey = "Kittaabb";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        noResultsPage = new NoResultsPage(driver);
        headerKeywordSearch = new HeaderKeywordSearch(driver);
    }
    //
    @Test(priority = 0, enabled = true)
    public void setHeaderKeywordSearchInput() {
        headerKeywordSearch.setHeaderSearchInput(searchKey);
    }
    //
    @Test(priority = 1, enabled = true)
    public void verifyHeaderSearchButtonClicked() {
//        Thread.sleep(200);
        headerKeywordSearch.clickHeaderSearchButton();
    }
    //
    @Test(priority = 2, enabled = true)
    public void verifyNoResultsReturned() {
        if (noResultsPage.getNoResultsReturned().contains("returned no results.")) {
        } else {
            logger.info(noResultsPage.getNoResultsReturned().toLowerCase());
            logger.info("*** No Results page failed ***");
        }
    }
    //
    @Test(priority = 3, enabled = true)
    public void verifyRecommendedForYou() {
        if (noResultsPage.getRecommendedForYou().contains("Recommended for You")) {
        } else {
            logger.info("*** Recommended for You not found ***");
        }
    }
    //
    @Test(priority = 4, enabled = true)
    public void verifyTopSellers() {
        if (noResultsPage.getTopSellers().contains("Overstock.com Top Sellers")) {
        } else {
            logger.info("*** Overstock.com Top Sellers not found ***");
        }
    }
    /*
    @Test
    public void noResultsPage() throws Exception {
        softAssert.assertEquals(driver.findElement(By.cssSelector("h3.rec-for-you.rec-header")).getText(), "Recommended for You", "Recommended for You not found");                // Recommended for You
//        Assert.assertTrue(driver.findElement(By.cssSelector("h3.rec-for-you.rec-header")).getText().equals("Recommended for You"), "Recommended for You not found");                // Recommended for You
        softAssert.assertEquals(driver.findElement(By.cssSelector("h3.top-sellers.rec-header")).getText(), "Overstock.com Top Sellers", "Overstock.com Top Sellers not found");    // Top Sellers header)
//        Assert.assertTrue(driver.findElement(By.cssSelector("h3.top-sellers.rec-header")).getText().equals("Overstock.com Top Sellers"), "Overstock.com Top Sellers not found");    // Top Sellers header
        softAssert.assertAll();
    }
    */
}