package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.RelatedReviews;
import com.overstock.sui.testbase.FusionResultPageUtils;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 10/3/2017.
 * SUI-868 - Redesign and reinstate SEO Reviews Widget - JL
 * Display Reviews Widget to Search pages
 * Verify Related Reviews are displayed below the SEO content description (at the end of the page)
 * Verify Reviews Widget on Nav pages
 * Verify Reviews Widget on Search pages
 * Verify the basic functionality, that it will show a carousel of product reviews of products that are currently showing on the page
 * Verify the data that is displayed is product title, product image, and review data
 * Verify the carousel pulls in the top 8 related reviews (it pulls 9 reviews)
 * Verify the carousel should pull in the top 8 related reviews (If on a given page no text for the review is available, it should display the product title, product image, and review star rating only. )
 * Verify that Related reviews should now only appear on the first page of the search results
 * Verify that clicking on the Product image takes the user to the product page (just like the product tile)
 * Verify clicking 'Read More' takes the user to the review section on the product page (allowing the user to read the remainder of the review text)
 * Verify clicking 'Read More' takes the user to the review section on the product page (allowing the user to read the remainder of the review text and Customer Reviews section should be expanded)
 * Verify Review Widget on Pagination (same related reviews carousel showed on pagination)
 * Verify Review Widget on Sort (Related Reviews carousel should change)
 * Verify Review Widget on Filter (Related Reviews carousel should change)
 * Verify Review Widget on Quick View
 * Verify Review Widget on More Like This
 * Verify Review Widget on Add to Cart
 * Verify Review Widget on Favorites
 * Verify Review Widget on Featured Products
 */

public class TC_RelatedReviews extends TestBase {
    RelatedReviews relatedReviews;
    FusionResultPageUtils fusionResultPageUtils;
    String baseUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        relatedReviews = new RelatedReviews(driver);
        fusionResultPageUtils = new FusionResultPageUtils(driver);
        driver.get(baseUrl);
    }
    //
    @Test(priority = 0, enabled = true, description = "Verify Related Reviews title is displayed")
    public void testRelatedReviewsTitle() throws NoSuchElementException {
        try {

//            Thread.sleep(5000);
//            String relatedReviewsSectionTitle = relatedReviews.getRelatedReviewsSectionTitle();
//            if (! (relatedReviewsSectionTitle.contains("Related Reviews"))) {
            if (!(relatedReviews.getRelatedReviewsSectionTitle().contains("Related Reviews"))) {
                logger.info("*** Related Reivews Title not found ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 1, enabled = true, description = "Verify Related Reviews Image is clickable")
    public void testRelatedReviewsImageClick() throws NoSuchElementException {
        try {
            relatedReviews.clickRelatedReviewsImage();
 //           Thread.sleep(3000);
            if (driver.getCurrentUrl().equals(baseUrl)) {
                logger.info("*** Related Reviews Image not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 2,enabled = true, description = "Verify Related Reviews Stars is displayed")
    public void testRelatedReviewsStars() throws NoSuchElementException {
        try {
            driver.get(baseUrl);
//            Thread.sleep(5000);
            String relatedReviewsStars = relatedReviews.getRelatedReviewsStars();
            if (Strings.isNullOrEmpty(relatedReviewsStars)) {
                logger.info("*** Related Reviews Stars not displayed ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 3, enabled = true, description = "Verify Related Reviews Text is displayed")
    public void testRelatedReviewsText() throws NoSuchElementException {
        try {
            String relatedReviewsText = relatedReviews.getRelatedReviewsText();
            if (Strings.isNullOrEmpty(relatedReviewsText)) {
                logger.info("*** Related Reviews Text not displayed ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test (priority = 4, enabled = true, description = "Verify Related Reviews Carousel Next Button is clickable")
    public void testRelatedReviewsArrowRight() throws NoSuchElementException {
        try {
            relatedReviews.clickRelatedReviewsArrowRight();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        Thread.sleep(500);
    }
    //
    @Test (priority = 5, enabled = true, description = "Verify Related Reviews Carousel Previous Button is clickable")
    public void testRelatedReviewsArrowLeft() throws NoSuchElementException {
        try {
            relatedReviews.clickRelatedReviewsArrowLeft();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        Thread.sleep(500);
    }
    //
    @Test(priority = 6, enabled = true, description = "Verify Related Reviews Read More link is clickable")
    public void testRelatedReviewsReadMore() throws NoSuchElementException {
        try {
            if (relatedReviews.clickRelatedReviewsReadMore()) {
//                Thread.sleep(2000);
                if (driver.getCurrentUrl().equals(baseUrl)) {
                    logger.info("*** Related Reviews Read More link not clicked ***");
                }
            } else {
                logger.info("*** Related Reviews Read More link not clicked ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 7, enabled = true)
    public void testRelatedReviewsNextPage() throws NoSuchElementException {
        try {
            fusionResultPageUtils.clickNextPage();
//        relatedReviews.clickRelatedReviewsNextPage();
//            Thread.sleep(1000);
            if (relatedReviews.getRelatedReviewsSectionTitle() !="false") {
                logger.info("*** Related Reviews should only be found on first page ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 8, enabled = true)
    public void testRelatedReviewsPreviousPage() throws NoSuchElementException {
        fusionResultPageUtils.clickPreviousPage();
//        relatedReviews.clickRelatedReviewsPreviousPage();
//        Thread.sleep(1000);
        if (relatedReviews.getRelatedReviewsTitle().toString()=="false") {
            logger.info("*** Related Reviews not found first page ***");
        }
    }
}
