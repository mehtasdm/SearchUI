package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.BreadcrumbKeywordSearch;
import com.overstock.sui.testbase.FusionResultPageUtils;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by Snehal on 1/16/2018.
 * BSD-365-BreadCrumb and Result count for Keyword Search

    Go to overstock.com homepage
    Perform keyword search for term 'Red Shoes'
    Apply "Red" color filter
    Apply "$80+" price filter
    Apply "Top rated" more ways to shop filter
    Remove the $80 and Top rated bread crumbs which are present at the top
    Now remove "Red" color filter as well
    Verify that Breadcrumb is removed and user is shown correct result count. Thus validate that Bread crumb and result count functionality works as required.
    Verify Breadcrumb links
 */
public class TC_BreadcrumbKeywordSearch extends TestBase {
    BreadcrumbKeywordSearch breadcrumbKeywordSearch;
    FusionResultPageUtils fusionResultPageUtils;
    //
    String strSearch = "Red Rugs";
    String testUrl;
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        breadcrumbKeywordSearch = new BreadcrumbKeywordSearch(driver);
        fusionResultPageUtils = new FusionResultPageUtils(driver);
    }
    //
    @Test(priority = 0, enabled = true)
    public void setBreadcrumbKeywordSearch() {
        fusionResultPageUtils.setKeywordSearch(strSearch);
        testUrl=driver.getCurrentUrl();
    }
    //
    @Test(priority = 1, enabled = true)
    public void verifySearchHeaderH1() {
        if (!breadcrumbKeywordSearch.getSearchHeaderH1().equals("Your Search Results for \"" + strSearch + "\"")) {
            logger.error("*** Search Results different " + breadcrumbKeywordSearch.getSearchHeaderH1() + " ***");
        }
    }
    //
    @Test(priority = 2, enabled = true)
    public void verifyAreThereBreadCrumbs() throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        if (breadcrumbKeywordSearch.getBreadcrumbLinkSize() == 0) {
            logger.error("*** No Breadcrumbs found for " + strSearch + " ***");
        }
    }
    //
    @Test(priority = 3, enabled = true)
    public void verifyBreadcrumbLinks() throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        for (int i = 0; i<= breadcrumbKeywordSearch.getBreadcrumbLinkSize(); i++) {
            breadcrumbKeywordSearch.clickBreadcrumbLink(i);
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.error("Breadcrumb Link " + breadcrumbKeywordSearch.getBreadcrumbLink(i) + " not clicked ***");
            }
            driver.navigate().back();
        }
    }
}
