package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.AvgCustomerReviews;
import com.overstock.sui.pagelibrary.SEOBigBottom;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

/**
 * SEO Big Bottom appears below bottom Brand Ad Banner (Brand Ad Banner appears below pagination)
 * SEO Big Bottom appears above Related Searches & Related Reviews
 */
public class TC_SEOBigBottom extends TestBase {
    SEOBigBottom seoBigBottom;
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    String testUrl="https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    //
    @BeforeTest
    private void setUp() throws IOException {
        init();
        seoBigBottom = new SEOBigBottom(driver);
        driver.get(testUrl);
    }
    //

}
