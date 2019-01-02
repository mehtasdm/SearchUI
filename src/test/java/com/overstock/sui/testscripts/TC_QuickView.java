package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.QuickView;
import com.overstock.sui.testbase.ExcelReader;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by smehta on 10/24/2017.
     * Search for sofas and couches
     * 0. On the Result page, hover mouse over a product
     * 0. Verify Quick View is displayed
     * 1. Click on Quick View button
     * Verify clicking Quick View button displays the modal window for the Product clicked
     * Verify clicking Quick View Product Title displays Product Page
     * Verify clicking Quick View Review Stars displays Product Page
     * Verify clicking Quick View Review Count displays Product Page
     * Verify clicking Quick View Image displays Product Page
     * Verify product description is displayed below the Reviews
     * Verify Price is displayed below the product description
     * Verify Quick View Qty is selectable
     * Verify Quick View w/o Options is working
     * Verify clicking Add to Cart in Quick View, adds the product to the Cart
     * Verify clicking Express Checkout in Quick View takes to Checkout page
     * Verify the left carousel thumbnail images are scrollable
     * Verify the left carousel thumbnail images click changes the Quick View image
     * Verify the Quick View Similar Items carousel is displayed on the right
     * Verify clicking the Similar Items from the carousel displays information of this product in the Quick View wrapper area (viz. the image, product description, price
     * Verify the Quick View icon-close closes the Quick View window
**/

public class TC_QuickView extends TestBase{
    QuickView quickView;
    ExcelReader excelReader;
    String testUrlNoOptions;
//    String testUrlWithOptions;
    String testUrlWithOptions= "https://www.overstock.com/Jewelry-Watches/Rings/2360/cat.html";
//    String testUrlNoOptions = "https://www.overstock.com/Clothing-Shoes/Outerwear/3407/cat.html";
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());

    @BeforeTest
    public void setUp() throws IOException {
        init();
        quickView = new QuickView(driver);
        excelReader = new ExcelReader(excelFileName);
//        testUrlWithOptions = excelReader.randomUrl();
        testUrlNoOptions = excelReader.randomUrl();
    }
    //
    @Test (priority = 0)
    public void testQuickViewClick() throws InterruptedException {
//        Thread.sleep(2000);
        clickQuickView(testUrlNoOptions);
        try {
            if (driver.findElements(By.cssSelector("div.quick-view-container")).size() == 0) {
                logger.info("*** Quick View Window not opened ***");
            }
        } catch (Exception e) {
            logger.info("*** Quick View Element not found ***");
        }
    }
    //
    @Test (priority = 1)
    public void testQuickViewProductTitleClicked() throws NoSuchElementException {
//        Thread.sleep(500);
        quickView.clickQuickViewProductTitle();
//        Thread.sleep(4000);
        logger.info(driver.getCurrentUrl());
        if (driver.getCurrentUrl().contains("search?keywords=bowls")) {
            logger.info("*** Quick View Product Title not clicked ***");
        }
    }
    //
    @Test (priority = 2)
    public void testQuickViewReviewStarsClicked() throws InterruptedException {
        clickQuickView(testUrlNoOptions);
        quickView.clickQuickViewReviewStars();
        if (driver.getCurrentUrl().equals(testUrlWithOptions)) {
            logger.info("*** Quick View Review Stars not clicked ***");
        }
    }
    //
    @Test (priority = 3)
    public void testQuickViewReviewCountClicked() throws InterruptedException {
        clickQuickView(testUrlNoOptions);
        quickView.clickQuickViewReviewCount();
        if (driver.getCurrentUrl().equals(testUrlWithOptions)) {
            logger.info("*** Quick View Review Count not clicked ***");
        }
    }
    //
    @Test (priority = 4)
    public void testQuickViewImageClicked() throws InterruptedException {
        clickQuickView(testUrlNoOptions);
        quickView.clickQuickViewImage();
        if (driver.getCurrentUrl().equals(testUrlWithOptions)) {
            logger.info("*** Quick View Image not clicked ***");
        }
    }
    //
    @Test (priority = 5)
    public void testQuickViewProdDesc() throws InterruptedException {
        clickQuickView(testUrlNoOptions);
        if (! quickView.getQuickViewProductDesc()) {
            logger.info("*** Quick View Product Description missing ****");
        }
    }
    //
    @Test (priority = 6)
    public void testQuickViewPrice() throws NoSuchElementException {
        if (! quickView.getQuickViewPrice()) {
            logger.info("*** Quick View Price missing ****");
        }
    }
    //
    @Test (priority = 7)
    public void testQuickViewQtyNoOptions() throws InterruptedException {
        quickView.setQuickViewQty(1);
    }
    //
    @Test (priority = 8)
    public void testQuickViewAddToCartNoOptionsClick() {
        quickView.clickQuickViewAddToCart();
//        Thread.sleep(2000);
        if (! quickView.getQuickViewAddToCartMessage()) {
            logger.info("*** Quick View Add to Cart not Clicked ***");
//            logger.info("*** Quick View Add to Cart not Clicked ***");
        }
    }
    //
    @Test (priority = 9)
    public void testQuickViewCheckoutNowNoOptionsClick() {
//        Thread.sleep(700);
        quickView.clickQuickViewCheckoutNow();
//        Thread.sleep(2000);
        if (driver.getCurrentUrl().equals(testUrlNoOptions)) {
            logger.info("*** Quick View Checkout Now not clicked ****");
        }
    }
    //
    @Test (priority = 10)
    public void testQuickViewExpressCheckoutNoOptionsClicked() throws InterruptedException {
        driver.get(testUrlNoOptions);
//        Thread.sleep(1000);
        clickQuickView(testUrlNoOptions);
        quickView.clickQuickViewExpressCheckout();
//        Thread.sleep(2000);
        if (driver.getCurrentUrl().equals(testUrlNoOptions)) {
            logger.info("*** Quick View Express Checkout not clicked ***");
        }
    }
    //
    @Test (priority = 11)
    public void testQuickViewQtyWithOptions() throws InterruptedException {
        clickQuickView(testUrlWithOptions);
        quickView.setQuickViewQty(1);
    }
    //
    @Test (priority = 12)
    public void testQuickViewOptionsClicked() throws InterruptedException {
//        quickView.setQuickViewQty(5);
        quickView.setQuickViewOptions(1);
    }
    //
    @Test (priority = 13)
    public void testQuickViewAddToCartWithOptionsClick() {
        quickView.clickQuickViewAddToCart();
        if (! quickView.getQuickViewAddToCartMessage()) {
            logger.info("*** Quick View Add to Cart With Options not Clicked ***");
        }
    }
    //
    @Test (priority = 14)
    public void testQuickViewCheckoutNowWithOptionsClick() {
//        Thread.sleep(700);
        quickView.clickQuickViewCheckoutNow();
//        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals(testUrlNoOptions)) {
            logger.info("*** Quick View Checkout Now With Options not clicked ****");
        }
    }
    //
    @Test (priority = 15)
    public void testQuickViewExpressCheckoutWithOptionsClicked() throws InterruptedException {
        driver.get(testUrlWithOptions);
//        Thread.sleep(3000);
        clickQuickView(testUrlWithOptions);
        quickView.clickQuickViewExpressCheckout();
//        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals(testUrlWithOptions)) {
            logger.info("*** Quick View Express Checkout With Options not clicked ***");
        }
    }
    //
    @Test (priority = 16)
    public void testQuickViewCarouselThumbnailImagesClick() throws InterruptedException {
        driver.get(testUrlWithOptions);
//        Thread.sleep(2000);
        clickQuickView(testUrlWithOptions);
//        Thread.sleep(1000);
        if (! quickView.clickQuickViewThumbnailImage()) {
            logger.info("*** Quick View Thumbnail Image not clicked ***");
        }
    }
    //
    @Test (priority = 17)
    public void testQuickViewCarouselThumbnailImgClickQuickViewChange() throws NoSuchElementException {
        //
    }
    //
    @Test (priority = 18)
    public void testQuickViewSimilarItemsCarousel() throws InterruptedException {
//        Thread.sleep(1000);
        if (! quickView.getQuickViewSimilarItems()) {
            logger.info("*** Quick View Similar Items Carousel not displayed ***");
        }
    }
    //
    @Test (priority = 19)
    public void testQuickViewSimilarItemsCarouselClick() throws InterruptedException {
//        Thread.sleep(1000);
        if (! quickView.clickQuickViewSimilarItemsCarousel()) {
            logger.info("*** Quick View Similar Items Carousel Image not clicked ***");
        }
    }
    //
    @Test (priority = 20)
    public void testQuickViewIconClose() throws InterruptedException, NoSuchElementException {
//        Thread.sleep(1000);
        quickView.clickQuickViewIconClose();
        try {
            if (driver.findElements(By.cssSelector("div.quick-view-container")).size() != 0) {
                logger.info("*** Quick View Window not closed ***");
            }
        } catch (Exception e) {
            logger.info("*** Quick View Element not found ***");
        }
    }
    //
    public void clickQuickView(String testUrl) throws InterruptedException, NoSuchElementException {
        try {
            driver.get(testUrl);
//            Thread.sleep(2000);
            quickView.quickViewClick(driver);
//            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
}
