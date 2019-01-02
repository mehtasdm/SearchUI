package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.Sort;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 4/10/2017.
 * Hover over Sort by
 * Sort by last element from the list
 * Verify that the baseUrl and sorted Url are different
 */
public class TC_Sort extends TestBase {
    private String testUrlNavigation = "https://www.overstock.com/Jewelry-Watches/Diamond-Earrings/14495/subcat.html";
    private String testUrlSearch = "https://www.overstock.com/search?keywords=kids+toys&taxonomy=sto5&ralt=sto35,sto34,sto9&TID=AR:TRUE&searchtype=Header";
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    Sort sort;
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        sort = new Sort(driver);
    }
    //
    @Test(priority = 0, enabled = true)
    public void sortByNavigationDummy() {
        // this test is just to set the url
        driver.get(testUrlNavigation);
    }
    //
    @Test(priority = 1, enabled = true, dataProvider = "sortByNavigation")
    public void sortByNavigation(String sortDropDown, String inSortUrl) throws NoSuchElementException, InterruptedException {
//        sort.hoverSortBy();   // removed hover behavior

        sort.setSortByDropdown(sortDropDown);
        Thread.sleep(500);
        if (!driver.getCurrentUrl().contains(inSortUrl)) {
            logger.error("*** Sorting failed for " + sortDropDown + " ***");
        }
    }
    @Test(priority = 2, enabled = true)
    public void sortBySearchDummy() {
        // this test is just to set the url
        driver.get(testUrlSearch);
    }
    //
    @Test(priority = 3, enabled = false, dataProvider = "sortBySearch")
    public void sortBySearch(String sortDropDown, String inSortUrl) throws NoSuchElementException, InterruptedException {
        driver.get(testUrlNavigation);
//        sort.hoverSortBy();
        sort.setSortByDropdown(sortDropDown);
        if (!driver.getCurrentUrl().contains(inSortUrl)) {
            logger.error("*** Sorting failed for " + sortDropDown + " ***");
        }
    }
    //
    @DataProvider(name = "sortByNavigation")
    public Object[][] provideDataSortByNavigation() {
        return new Object[][]{
//            {"On Sale", "?sort=On%20Sale"},
//            {"Best Selling", "?sort=Best%20Selling"},
            {"Price Low - High", "?sort=Price%20Low%20-%20High"},
            {"Price High - Low", "?sort=Price%20High%20-%20Low"},
//            {"Customer Rating", "?sort=Avg.%20Customer%20Rating"}
//            {"New Arrivals", "?sort=New%20Arrivals"}
        };
    }
    //
    @DataProvider(name = "sortBySearch")
    public Object[][] provideDataSortBySearch() {
        return new Object[][]{
            {"Price Low - High", "?sort=Price%20Low%20-%20High"},
            {"Price High - Low", "?sort=Price%20High%20-%20Low"},
            {"Clearance", "?sort=Clearance"},
            {"New Products", "?sort=New%20Products"},
            {"Top Rated", "?sort=Top%20Rated"},
            {"On Sale", "?sort=On%20Sale"},
            {"Best Selling", "?sort=Best%20Selling"}

//            {"Best Matched", "?sort=Best%20Matched"},
//            {"Avg. Customer Rating", "?sort=Avg.%20Customer%20Rating"},
//            {"New Arrivals", "?sort=New%20Arrivals"}
        };
    }
    //
}
