package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.Pagination;
import com.overstock.sui.pagelibrary.Sort;
import com.overstock.sui.pagelibrary.TopCarousel;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 12/23/2017.
 * https://www.overstock.com/Home-Garden/Area-Rugs/Outdoor,/rug-type,/244/cat.html?TID=ARSP:00:LeftNav-4:Indoor/OutdoorRugs
 * Verify the right arrow on Carousel is clickable
 * Verify the left arrow on Carousel is clickable
 * Verify the taxonomy tile in Carousel is clickable
 * Verify on click of taxonomy tile takes to subcat page
 * Verify Top Carousel is persistent (i.e. it appears on every page, on SPA actions viz. sort, filter)
 * Verify Top Carousel should not appear on pagination
 * Verify Carousel for Recommended for You and Top Sellers when results not found , https://www.overstock.com/search?keywords=fadsf+fdasfd&SearchType=Header
 */

public class TC_TopCarousel extends TestBase {
    String testUrl = "https://www.overstock.com/Jewelry-Watches/Engagement-Rings/Emerald,/stone-shape,/14657/subcat.html";
    TopCarousel  topCarousel;
    Sort sort;
    Pagination pagination;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        topCarousel = new TopCarousel(driver);
        sort = new Sort(driver);
        pagination = new Pagination(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    public void verifyArrowLeftDisabledOnPageLoad() throws NoSuchElementException {
        // Initial State of Arrow Left should be disabled
        try {
            if (!topCarousel.verifyArrowLeftDisabled()) {
                logger.info("*** Initial State of Arrow Left not Disabled ***");
            }
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 1, enabled = true)
    public void verifyArrowRightEnabledOnPageLoad() throws NoSuchElementException {
        // Initial State of Arrow Left should be disabled
        try {
            if (!topCarousel.verifyArrowRightEnabled()) {
                logger.info("*** Initial State of Arrow Right not Enabled ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 2, enabled = true, dependsOnMethods = {"verifyArrowRightEnabledOnPageLoad"})
    public void verifyClickArrowRight() throws NoSuchElementException {
        try {
            topCarousel.clickArrowRight();
            if (!topCarousel.verifyArrowLeftEnabled()) {
                logger.info("*** Arrow Right not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 3, enabled = true, dependsOnMethods = {"verifyClickArrowRight"})
    public void verifyClickArrowLeft() throws NoSuchElementException, InterruptedException {
        Thread.sleep(1000);
        try {
            topCarousel.clickArrowLeft();
            if(!topCarousel.verifyArrowRightEnabled()) {
                logger.info("*** Arrow Left not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 4, enabled = true)
    public void verifyClickRefinementImage() {
        try {
            topCarousel.clickRefinementImage();
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.info("*** Refinement Image not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 5, enabled = true)
    public void verifyClickRefinementLink() throws NoSuchElementException {
        driver.get(testUrl);
        try {
            topCarousel.clickRefinementLink();
            if (driver.getCurrentUrl().equals(testUrl)) {
                logger.info("*** Refinement Link not clicked ***");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    //
    @Test(priority = 6, enabled = true)
    public void verifyCarouselOnSort() throws InterruptedException {
        driver.get(testUrl);
        String sortBy = "Price Low - High";
        String inSortUrl = "?sort=Price%20Low%20-%20High";
        Thread.sleep(1000);
        sort.hoverSortBy();
        Thread.sleep(1000);
        sort.setSortByDropdown(sortBy);
        if (!driver.getCurrentUrl().contains(inSortUrl)) {
            logger.error("*** Sorting failed for " + sortBy + " ***");
        }
        if (!topCarousel.isCarouselPresent()) {
            logger.error("*** Carousel not present on Sort SPA action ***");
        }
    }
    //
    @Test(priority = 7, enabled = true)
    public void verifyCarouselOnPagination() {
        pagination.clickPageNext();
        if (topCarousel.isCarouselPresent()) {
            logger.error("*** Carousel should be present only on First page ***");
        }
    }
    //
}
