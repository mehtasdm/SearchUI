package com.overstock.sui.testscripts;

import com.overstock.sui.customlistener.ListenerTest;
import com.overstock.sui.pagelibrary.BrandAdBanner;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.IOException;

/**
 * Verify Single or Double Brand Ad Banner below Pagination and above Related Reviews
 * Verify brand-ad-image
 * Verify brand-ad-logo
 * Verify brand-ad-savings      // Up to 60% off | Up to 60% off + Extra 35% off | Save on
 * Verify Title                 // Select Window Treatments | Everything for Your Home
 * Verify click on link
 */

@Listeners(ListenerTest.class)
public class TC_BrandAdBanner extends TestBase {
    //
    BrandAdBanner brandAdBanner;
    String testUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        brandAdBanner=new BrandAdBanner(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    private void numberOfBrandAdBanner() {
        if (brandAdBanner.countBrandAdBanner()==0 | brandAdBanner.countBrandAdBanner()>2) {
            logger.error("*** Brand Ad Banners count = " + brandAdBanner.countBrandAdBanner());
        }
    }
    //
    @Test(priority = 1, enabled = true)
    private void numberBrandAdBannerLogo() {
        if (brandAdBanner.countBrandAdBannerLogo()==0 | brandAdBanner.countBrandAdBannerLogo()>2) {
            logger.error("*** Brand Ad Banner Logo count = " + brandAdBanner.countBrandAdBannerLogo());
        }
    }
    //
    @Test(priority = 2, enabled = true)
    private void numberBrandAdBannerImage() {
        if (brandAdBanner.countBrandAdBannerImage()==0 | brandAdBanner.countBrandAdBannerImage()>2) {
            logger.error("*** Brand Ad Banner Image count = " + brandAdBanner.countBrandAdBannerImage());
        }
    }
    //
    @Test(priority = 3, enabled = true)
    private void brandAdBannerTitle() {
        for (int i = 0; i <= brandAdBanner.countBrandAdBanner(); i++) {
            if (Strings.isNullOrEmpty(brandAdBanner.titleBrandAdBanner(i))) {
                logger.error("*** Brand Ad Banner Title = " + brandAdBanner.titleBrandAdBanner(0));
            }
        }
    }
    //
    @Test(priority = 4, enabled = true)
    private void brandAdBannerClick() {
        for (int i = 0; i <= brandAdBanner.countBrandAdBanner(); i++) {
            brandAdBanner.clickBrandAdBanner(i);
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.error("*** Brand Ad Banner [" + i + "] not clicked");
            }
            driver.get(testUrl);
        }
    }
    //
}
