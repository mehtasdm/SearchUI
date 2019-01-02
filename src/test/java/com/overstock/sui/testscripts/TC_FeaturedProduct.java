package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.FeaturedProduct;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * BSD-369-pSrch-Feature Product
 *
 * Go to Featured product result page with below url:-
 * https://www.overstock.com/search?keywords=red+shoes&taxonomy=sto7&ralt=sto6,sto33,sto1&TID=AR:TRUE&searchtype=Header&featuredproduct=7333212&featuredoption=9926807"  -- User should see the featured product at top.
 *
 * 1. Verify that click on image takes user to product detail page. -- User should be taken to product detail page.
 * 2. Verify that click on product name takes user to product detail page. -- User should be taken to product detail page.
 * 3. Verify that click on Reviews takes user to product detail page -- User should be taken to product detail page.
 * 4. Verify that click on Reviews count takes user to product detail page -- User should be taken to product detail page.
 * Verify that user can choose the required option from drop down. -- Users should be able to choose the required option from drop down.
 * Verify that user can choose the required Quantity -- Users should be able to choose the required Quantity
 * 5. Click on "Add to Cart" button -- Product should be added to cart with correct price.
 * 6. Click on back button -- User should be taken to product title page and featured product should be displayed at top.
 * Verify for featured products with no options
 * Verify for featured products out of stock
 * Featured Products with Free Shipping
 * Featured Products with no Free Shipping
 * After Add to Cart click, (Qty and Options should not be selectable)
 */

public class TC_FeaturedProduct extends TestBase {
    FeaturedProduct featuredProductTest;
    //
//    String baseUrlWithOptions = "https://www.overstock.com/search?keywords=red+shoes&taxonomy=sto7&ralt=sto6,sto33,sto1&TID=AR:TRUE&searchtype=Header";
//    String baseUrlWithOptions = "https://www.overstock.com/Jewelry-Watches/Watches/Rado,(900,),/brand,price,/292/dept.html?featuredproduct=9437322";
//    String baseUrlNoOptions = "https://www.overstock.com/Home-Garden/Wall-Sculptures/29370/cat.html?featuredproduct=8749745";
//    String noFreeShippingUrl = "https://www.overstock.com/search?keywords=red+shoes&taxonomy=sto7&ralt=sto6,sto33,sto1&TID=AR:TRUE&searchtype=Header&featuredproduct=14410658";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
//    String[] afeatureProductTest = {"featureProductImg","featureProductTitle","featureProductReview",
//        "featureProductReviewcount","featureProductAddToCart", "TopNavigation" };
    //
    @BeforeTest
    private void setUp() throws IOException {
        init();
        featuredProductTest = new FeaturedProduct(driver);
    }
    //
    @Test(priority = 0, enabled = true, dataProvider = "dataFeaturedProductOption")
    public void testFeaturedProduct(String testUrl, String fpOptions) throws NoSuchElementException, InterruptedException {
        testFeaturedProductImg(testUrl, "Feature product Image " + fpOptions + " clicked", "*** Feature product Image " + fpOptions +" not clicked ***" );
        testFeaturedProductTitle(testUrl, "Feature product Title " + fpOptions + " clicked", "*** Feature product Title " + fpOptions + " not clicked ***" );
        testFeaturedProductPrice(testUrl,"Feature product Price " + fpOptions + " displayed","*** Feature product Price " + fpOptions + " not displayed ***");
        testFeaturedProductFreeShipping("Featured Product " + fpOptions + " Free Shipping displayed", "*** Featured Product " + fpOptions + " Free Shipping Not displayed ***");
        testFeaturedProductReview();
        testFeaturedProductReviewCount(testUrl);
        driver.get(testUrl);
        //
        boolean isSuccess = false;
        if (fpOptions.equals("WO")) {
            featuredProductTest.featuredProductAddToCartClick();
            try {
                isSuccess = featuredProductTest.featuredProductAddToCartMessage();
            } catch (Exception e) {
//                logger.error(e.getMessage());
            }
            if (isSuccess) {
                logger.info("*** Feature product should not Add To Cart without Options selected ***");
            } else {
                logger.info("Feature product Add To Cart not clicked -- Ok");
            }
        }
        //
        featuredProductTest.setFeaturedProductQty(1);
        if (fpOptions.equals("WO")) {
            Thread.sleep(300);
            featuredProductTest.setFeaturedProductOptions(2);       // Run this test only for Featured Products with Options
        }
        Thread.sleep(100);
        featuredProductTest.featuredProductAddToCartClick();
        Thread.sleep(100);
        if (!featuredProductTest.featuredProductAddToCartMessage()) {
            logger.info("*** Feature Product Add to Cart Message for " + fpOptions + " not displayed ***");
        }
        testFeaturedProductCheckoutNow("Feature product Checkout Now " + fpOptions + " clicked","*** Feature product Checkout Now " + fpOptions + " Not clicked ***");
        testFeaturedProductBackButton(testUrl);
        driver.get(testUrl);
//        Thread.sleep(1000);
        isSuccess = featuredProductTest.topNavIsClickable();
        if (isSuccess) {
            logger.info("Feature product Top Nav clicked");
        }
        else { logger.info("*** Feature product Top Nav not clicked ****"); }
    }
    //
    @AfterTest
    private void tearDown() {
        //driver.close();
    }
    //
    private void testFeaturedProductImg(String baseUrl, String msgPass, String msgFail) throws NoSuchElementException, InterruptedException {
        driver.get(baseUrl);
 //       Thread.sleep(1000);
        boolean isSuccess = featuredProductTest.featuredProductImgClick();
        if (isSuccess) {
            logger.info(msgPass);
        }
        else { logger.info(msgFail); }
    }
    //
    private void testFeaturedProductTitle(String baseUrl, String msgPass, String msgFail) throws NoSuchElementException, InterruptedException {
        driver.get(baseUrl);
//        Thread.sleep(1000);
        boolean isSuccess = featuredProductTest.featuredProductTitleClick();
        if (isSuccess) {
            logger.info(msgPass);
        }
        else { logger.info(msgFail); }
    }
    //
    private void testFeaturedProductPrice(String baseUrl, String msgPass, String msgFail) throws NoSuchElementException, InterruptedException {
        driver.get(baseUrl);
//       Thread.sleep(1000);
        if (!Strings.isNullOrEmpty(featuredProductTest.getFeaturedProductPrice())) {
            logger.info(msgPass);
        } else
            logger.info(msgFail);
    }
    //
    private void testFeaturedProductReview() {
        boolean isSuccess = featuredProductTest.featuredProductReviewClick();
        if (isSuccess) {
            logger.info("Feature product Review Stars clicked");
        }
        else { logger.info("*** Feature product Review Stars not clicked ****"); }
    }
    //
    private void testFeaturedProductReviewCount(String testUrl) {
        driver.get(testUrl);
        boolean isSuccess = featuredProductTest.featuredProductReviewCountClick();
        if (isSuccess) {
            logger.info("Feature product Review Count clicked");
        }
        else { logger.info("*** Feature product Review Count not clicked ****"); }
    }
    //
    private void testFeaturedProductFreeShipping(String msgPass, String msgFail) throws NoSuchElementException, InterruptedException {
        if (featuredProductTest.featuredProductFreeShipping()) {
            logger.info(msgPass);
        }
        else
            logger.info(msgFail);
    }
    //
/*
    private void testFeaturedProductAddToCart() throws Exception {
//        Thread.sleep(1000);
        featuredProductTest.featuredProductAddToCartClick();
        if (isSuccess) {
            logger.info("Feature product Add To Cart clicked");
        }
        else { logger.info("*** Feature product Add To Cart not clicked ****"); }
    }
*/
    //
    private void testFeaturedProductCheckoutNow(String msgPass, String msgFail) throws NoSuchElementException, InterruptedException {
//        Thread.sleep(1000);
        boolean isSuccess = featuredProductTest.featuredProductCheckoutNowClick();
        if (isSuccess) {
            logger.info(msgPass);
        }
        else { logger.info(msgFail); }
    }
    //
    private void testFeaturedProductBackButton(String baseUrl) throws NoSuchElementException, InterruptedException {
//        Thread.sleep(3000);
        featuredProductTest.featuredProductBackButton();
        if (driver.getCurrentUrl().equals(baseUrl)) {
//            logger.info("Featured Product Back button looks good");
        }
        else {logger.info("*** Featured Product Back button does not look good ***"); }
    }
    //
    @DataProvider(name = "dataFeaturedProductOption")
    public Object[][] provideDataFeaturedProduct() {
        return new Object[][]{
            {"https://www.overstock.com/Jewelry-Watches/Watches/Rado,(900,),/brand,price,/292/dept.html?featuredproduct=9437322", "WO"}, // With Options
            {"https://www.overstock.com/Home-Garden/Wall-Sculptures/29370/cat.html?featuredproduct=8749745", "NO"}  // No Options
        };
    }
    //
}
