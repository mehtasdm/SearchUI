package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.HeaderKeywordSearch;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Snehal on 4/16/2017.
 * BSD-365-Bread Crumb and Result count for Keyword Search

    Go to overstock.com homepage
    Perform keyword search for term 'Red Shoes'
    Apply "Red" color filter
    Apply "$80+" price filter
    Apply "Top rated" more ways to shop filter
    Remove the $80 and Top rated bread crumbs which are present at the top
    Now remove "Red" color filter as well
    Verify that Bread crumb is removed and user is shown correct result count. Thus validate that Bread crumb and result count functionality works as required.
 */
public class TC_HeaderKeywordSearch extends TestBase {
    HeaderKeywordSearch headerKeywordSearch;
//    String testUrl = "https://wwww.overstock.com";
    String searchKey = "red rugs";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
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
        headerKeywordSearch.clickHeaderSearchButton();
    }
    //
    @Test(priority = 2, enabled = true)
    public void verifyHeaderSearchNavH1() {
        logger.info(headerKeywordSearch.getHeaderSearchNavH1());
        if (!headerKeywordSearch.getHeaderSearchNavH1().toLowerCase().contains(searchKey)) {
            logger.info("*** Search Term not found ***");
        }
    }
    //
    @Test(priority = 3, enabled = true)
    public void verifyHeaderSearchUrl() {
        if (!driver.getCurrentUrl().contains("search?keywords=red+rugs")) {
            logger.error("*** Search Term Not found in Url ***");
        }
    }
    //
}
