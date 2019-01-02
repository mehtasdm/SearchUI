package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.ProductTile;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 12/26/2017
 * On Fusion search page, click on any of the product
 * The product details page should be displayed
 * Click on the browser back button on product details page, the fusion search page should be displayed
 *
 * Verify clicking Product Title displays Product Page
 * Verify clicking Review Stars displays Product Page
 * Verify clicking Review Count displays Product Page
 * Verify clicking Image displays Product Page
 * Verify product description is displayed above the Reviews
 * Verify Price is displayed above the product description
 * Verify Price Range - $137.99 - $177.99
 * Verify Price - $454.99
 * Verify MSRP price - $629.00
 * Verify $174.01 OFF
 * Verify "See Price in Cart"
 * Verify Quick View w/o Options is working
 *
 */
public class TC_ProductTile extends TestBase {
    //
    String testUrl = "https://www.overstock.com/Jewelry-Watches/Engagement-Rings/Emerald,/stone-shape,/14657/subcat.html";
    ProductTile productTile;
    //
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        productTile = new ProductTile(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    public void verifyProductTileSaleOnly() throws NoSuchElementException {
        if (!productTile.verifyProductTileSaleOnlyDisplayed()) {
            logger.info("*** This Prodict Tile does not have SALE Tag ***");
        }
    }
    //
    @Test(priority = 1, enabled = true)
    public void verifyProductTileWishListHearth() throws NoSuchElementException {
        if (!productTile.verifyProductTileWishListHearthDisplayed()) {
            logger.error("*** Wish List Heart missing on this Prodict Tile ***");
        }
    }
    //
    @Test(priority = 2, enabled = true)
    public void verifyProductTileImg() throws NoSuchElementException {
        if (!productTile.verifyProductTileImgDisplayed()) {
            logger.error("*** Image missing on this Prodict Tile ***");
        }
    }
    //
    @Test(priority = 4, enabled = true)
    public void verifyProductTileMoreOptions() throws NoSuchElementException {
        // Not all the Product Tile will have More Options
        try {
            if (!productTile.verifyProductTileMoreOptionsTextDisplayed()) {
                logger.info("*** This Product Tile does not have More Options Text ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 5, enabled = true)
    public void verifyProductTilePriceType() throws NoSuchElementException {
        // Not all the Product Tile will be on Sale
        try {
            if (!productTile.verifyProductTilePriceTypeDisplayed()) {
                logger.info("*** This Product is not for Sale ***");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //
    @Test(priority = 6, enabled = true)
    public void verifyProductTileFromPriceSign() throws NoSuchElementException {
        if (!productTile.verifyProductTileFromPriceSignDisplayed()) {
            logger.error("*** This Product Tile is missing From $ ***");
        }
    }
    //
    @Test(priority = 7, enabled = true)
    public void verifyProductTileFromPriceDollar() throws NoSuchElementException {
        logger.info("Price From Dollars - " + productTile.verifyProductTileFromPriceDollarDisplayed());
    }
    //
    @Test(priority = 8, enabled = true)
    public void verifyProductTileFromPriceCents() throws NoSuchElementException {
        logger.info("Price From Cents - " + productTile.verifyProductTileFromPriceCentDisplayed());
    }
    //
    @Test(priority = 9, enabled = true)
    public void verifyProductTileToPriceSign() throws NoSuchElementException {
        // Not all Products will have To (Only if the price is a range)
        try {
            if (!productTile.verifyProductTileToPriceSignDisplayed()) {
                logger.info("*** This Product Tile is missing To $ ***");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    //
    @Test(priority = 10, enabled = true)
    public void verifyProductTileToPriceDollar() throws NoSuchElementException {
        // Not all Products will have To (Only if the price is a range)
        try {
            logger.info("Price To Dollars - " + productTile.verifyProductTileToPriceDollarDisplayed());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    //
    @Test(priority = 11, enabled = true)
    public void verifyProductTileToPriceCents() throws NoSuchElementException {
        // Not all Products will have To (Only if the price is a range)
        try {
            logger.info("Price To Cents - " + productTile.verifyProductTileToPriceCentDisplayed());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    //
    @Test(priority = 12, enabled = true)
    public void verifyProductTileMSRPWas() throws NoSuchElementException {
        if (!(productTile.verifyProductTileMSRPWasDisplayed())) {
            logger.error("*** MSRP or Was not found in Product Tile");
        }
    }
    //
    @Test(priority = 14, enabled = true)
    public void verifyProductTileReferencePrice() throws NoSuchElementException {
        logger.info("MSRP Price - " + productTile.verifyProductTileReferencePriceDisplayed());
    }
    //
    @Test(priority = 15, enabled = true)
    public void verifyProductTileYouSavePrice() throws NoSuchElementException {
        logger.info("You Saved Price - " + productTile.verifyProductTileYouSavePriceDisplayed());
    }
    //
    @Test(priority = 16, enabled = true)
    public void verifyProductTileProdTitle() throws NoSuchElementException {
        if (Strings.isNullOrEmpty(productTile.verifyProductTileProdTitleDisplayed())) {
            logger.error("*** Product Title is missing for this product tile ***");
        }
    }
    //
    @Test(priority = 17, enabled = true)
    public void verifyProductTileReviewsStars() throws NoSuchElementException {
        if (!productTile.verifyProductTileReviewsStarsDisplayed()); {
            logger.error("*** Product Tile is missing Reviews ***");
        }
    }
    //
    @Test(priority = 18, enabled = true)
    public void verifyProductTileReviewsCount() throws NoSuchElementException {
        if (!productTile.verifyProductTileReviewsCountDisplayed()); {
            logger.error("*** Product Tile is missing Reviews Count ***");
        }
    }
    //
    @Test(priority = 19, enabled = true)
    public void clickProductTileImg() throws NoSuchElementException {
        productTile.clickProductTileImg();
        if (driver.getCurrentUrl().equals(testUrl)) {
            logger.error("*** Product Tile not clicked");
        }
//        driver.navigate().back();
    }
    //
}
