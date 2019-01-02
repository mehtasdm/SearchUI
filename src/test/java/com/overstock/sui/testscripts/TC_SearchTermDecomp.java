package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.HeaderKeywordSearch;
import com.overstock.sui.pagelibrary.SearchTermDecomp;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 4/19/2017.
 * BSD-366-Product Search-Search Term Decomposition
 * Verify below sections are displayed with Search Term Decomp
 * Search Term Decomp
 * Recommended for You
 * Overstock.com Top Seller
 */
public class TC_SearchTermDecomp extends TestBase {
    // private String twoWordSearch = "Ninja Shoes";
    // private String threeWordSearch = "Red Ninja Shoes";
    // private String fourWordSearch = "Park Bridgett Duvet Toy";
    HeaderKeywordSearch headerKeywordSearch;
    SearchTermDecomp searchTermDecomp;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    String[] wordSearch = {"Ninja Shoes", "Red Ninja Shoes", "Park Bridgett Duvet Toy"};
    String searchPattern = "Ninja|Shoes|Red|Park|Bridgett|Duvet|Toy";
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        headerKeywordSearch = new HeaderKeywordSearch(driver);
        searchTermDecomp = new SearchTermDecomp(driver);
    }
    //
    @Test(priority = 0, enabled = true, dataProvider = "keywordSearch")
    public void searchTermDecomp(String kSearch, int sTDCount) throws InterruptedException, NoSuchElementException {
        //
        try {
            logger.info(kSearch);
            headerKeywordSearch.setHeaderSearchInput(kSearch);
            headerKeywordSearch.clickHeaderSearchButton();
//            Thread.sleep(3000);
            if (searchTermDecomp.verifyNoResults()) {
                searchTermDecomp.verifyh1SearchNav();
                searchTermDecomp.verifyBreadCrumbContainer();
            } else {
                logger.info("*** Search Term Decomp not found for " + kSearch + " ***");
            }
            if (searchTermDecomp.verifySearchTermDecomp() != sTDCount) {
                logger.info("*** Search Term Decomp count does not match for " + kSearch + " ***");
            }
            searchTermDecomp.clickSeeAll();
            if (driver.getCurrentUrl().equals(baseUrl)) {
                logger.info("*** See All not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
 //       Thread.sleep(3000);
    }
    //
    @DataProvider(name = "keywordSearch")
    public Object[][] provideData() {
      return new Object[][] {
          {"Aaroon Bhatt", 2},
          {"Aaroon Smith Bhatt", 5},
          {"Park Bridgette Duvet Toy", 10}
      };
    }
    //
}