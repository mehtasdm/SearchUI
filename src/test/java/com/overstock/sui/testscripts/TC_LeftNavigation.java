package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.LeftNavigation;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Verify Delivery 2-Day Shipping is displayed
 * Top 5 Refinements should be expanded
 * Rest Refinements should be collapsed
 * Expand a Refinement
 * Collapse a Refinement
 * Refinement filters get applied (Apply Price filter)
 * Search within
 * Verify Refinement filter applied on collapsed filters should get expanded
 * Verify Refinement filter removed on collapsed/expanded filters should stay expanded
 * Refinements breadcrumb
 * Clear All link visible when two or more refinements are applied
 **/

public class TC_LeftNavigation extends TestBase {
    LeftNavigation leftNavigation;
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    String testUrl = "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    String strZipCode = "84094";
    int refineNum=8;
    int priceMin=50;
    int priceMax=200;
    boolean openAllRefinements=true;
    //
    @BeforeTest
    private void setUp() throws IOException {
        init();
        leftNavigation = new LeftNavigation(driver);
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    private void verifyDeliveryHeading() {
        if (!leftNavigation.getDeliveryHeading().contains("Delivery")) {
            logger.error("*** Delivery heading missing ***");
        }
    }
    //
    @Test(priority = 1, enabled = true)
    private void verifyFreeShippingText() {
        if (!leftNavigation.getFreeShippingText().contains("2-Day Shipping")) {
            logger.error("*** 2-Day Shipping text missing ***");
        }
    }
    //
    @Test(priority = 2, enabled = true)
    private void verifyZipcodeText() {
        if (!leftNavigation.getZipCode().contains("Zip Code :")) {
            logger.error("*** Zip Code : missing ***");
        }
    }
    //
    @Test(priority = 3, enabled = true)
    private void verifyLeftNavRefinementsExpanded() {
        if (!leftNavigation.areLeftNavRefinementsExpanded(openAllRefinements)) {
            logger.error("*** Left Nav Top 6 Not Expanded ***");
        }
    }
    //
    @Test(priority = 4, enabled = true)
    private void verifyLeftNavRefinementsCollapsed() {
        if (!leftNavigation.areLeftNavRefinementsCollapsed(openAllRefinements)) {
            logger.error("*** Left Nav Not Collapsed ***");
        }
    }

    //
    @Test(priority = 5, enabled = true, dependsOnMethods = "verifyZipcodeText")
    private void enterZipCode() throws NoSuchElementException {
        leftNavigation.setZipCode(strZipCode);
    }
    //
    @Test(priority = 6, enabled = true, dependsOnMethods = "enterZipCode")
    private void verifyFreeShippingIsSelectedH1() throws InterruptedException {
        Thread.sleep(500);
        if (!leftNavigation.freeShippingIsSelectedH1()) {
            logger.error("*** with Free 2-Day Shipping missing ***");
        }
    }
    //
    @Test(priority = 7, enabled = true, dependsOnMethods = "enterZipCode")
    private void verifyFreeShippingIsSelectedCheckbox() {
        if (!leftNavigation.freeShippingIsSelectedCheckbox()) {
            logger.error("*** 2-Day Shipping not checked ***");
        }
    }
    //
    @Test(priority = 8, enabled = true, dependsOnMethods = "enterZipCode")
    private void verifyFreeShippingIsSelectedRefinement() {
        if (!leftNavigation.freeShippingIsSelectedRefinement()) {
            logger.error("*** Shipping: 2-Day Delivery missing ***");
        }
    }
    //
    @Test(priority = 9, enabled = true)
    private void applyAndExpandRefinement() {
        int before=leftNavigation.numOfRefinementsExpanded();
        leftNavigation.expandRefinement(refineNum);
        refinementMessage(before, "*** Refinement not Expanded ***");
        /*
        int after=leftNavigation.numOfRefinementsExpanded();
        if (before==after || (before > after)) {
            logger.error("*** Refinement not Expanded ***");
        }
        */
    }
    //
    @Test(priority = 10, enabled = true, description = "It should not collapse refinement on filter applied")
    private void checkCollapseOnAppliedRefinement() {
        int before=leftNavigation.numOfRefinementsExpanded();
        leftNavigation.collapseRefinement(refineNum);
        refinementMessage(before, "*** Refinement not Collapsed ***");
        /*
        int after=leftNavigation.numOfRefinementsExpanded();
        if (before==after || (before < after)) {
            logger.error("*** Refinement not Collapsed ***");
        }
        */
    }
    //
    @Test(priority = 11, enabled = true)
    private void checkPriceRangeRefinement() throws InterruptedException {
        leftNavigation.setMinPrice(priceMin);
        leftNavigation.setMaxPrice(priceMax);
        leftNavigation.clickPriceGoButton();
        // Verify the price restriction has "Price: $888 - $999"
    }
    //
    @Test(priority = 12, enabled = true)
    private void checkMinPriceRefinement() {
        int before=leftNavigation.numOfRefinementsExpanded();
        leftNavigation.setMinPrice(priceMin);
        leftNavigation.clickPriceGoButton();
        // Verify the price restriction has "Price: Over $999"
        refinementMessage(before, "*** Refinement not Collapsed ***");
        /*
        int after=leftNavigation.numOfRefinementsExpanded();
        if (before==after || (before < after)) {
            logger.error("*** Refinement not Collapsed ***");
        }
        */
    }
    //
    @Test(priority = 14, enabled = true)
    private void checkMaxPriceRefinement() {
        leftNavigation.setMaxPrice(priceMax);
        leftNavigation.clickPriceGoButton();
        // Verify the price restriction has "Price: Under $999"
    }
    //
    @Test(priority = 14, enabled = true)
    private void checkPriceRefinement() {
        leftNavigation.applyPriceRefinement(2);
    }
    //
/*
    @Test(priority = 15, enabled = true)
    private void setInnerRefinement() throws InterruptedException {
        leftNavigation.setSearchWithin("Colors", "Red");
    }
*/
    //
    @Test(priority = 16, enabled = true, dataProvider = "leftNavRefinements")
    private void clickLeftNavRefinement(String refine, String refineValue) {
        leftNavigation.setSearchWithin(refine, refineValue);
        leftNavigation.clickInnerRefinement(refineValue);
    }
    //
    @Test(priority = 25, enabled = true)
    private void checkClearAll() {
        if (leftNavigation.numOfRefinementsOnTopAppllied() > 1) {                   // Clear All displayed only when there are more than 2 Refinements Applied
            leftNavigation.clickClearAll();
            if (leftNavigation.numOfRefinementsOnTopAppllied() > 0) {               // Refinements Applied=0, then Clear All successful
                logger.error("*** Click All not clicked");
            }
        } else { logger.info("Less than 2 Refinements applied, hence Clear All not visible"); }
    }
    //
    private void refinementMessage(int before, String msg) {
        int after=leftNavigation.numOfRefinementsExpanded();
        if (before==after || (before < after)) {
            logger.error(msg);
        }
    }
    //
    @DataProvider(name = "leftNavRefinements")
    private Object[][] provideDataLeftNavigation() {
        return new Object[][]{
//            {"Sales & Promotions", "Clearance"},
            {"Sales & Promotions", "On Sale"},
/*
            {"Colors", "Grey"},
            {"Colors", "Blue"},
            {"Colors", "Ivory"},
            {"Colors", "Natural"},
            {"Colors", "Silver"},
            {"Colors", "Red"},
            {"Colors", "Navy"},
            {"Colors", "Beige"},
            {"Colors", "Tan"},
            {"Colors", "Gold"},
            {"Colors", "Khaki"},
            {"Colors", "Teal"},
            {"Colors", "Burgundy"},
            {"Colors", "Olive"},
            {"Colors", "Yellow"},
            {"Rug Sizes", "8' x 10'"},
            {"Rug Sizes", "9' x 12'"},
            {"Rug Sizes", "7' x 9'"},
            {"Rug Sizes", "10' x 12'"},
            {"Rug Sizes", "7' x 11'"},
            {"Rug Sizes", "8' x 8'"},
            {"Rug Sizes", "9' x 9'"},
            {"Rug Sizes", "8'"},
            {"Price", "Under $250"},
            {"Price", "$250 - $400"},
            {"Price", "$400 - $600"},
            {"Price", "$600 - $1,500"},
            {"Price", "$1,500+"},
            {"Styles", "Contemporary"},
            {"Styles", "Modern"},
            {"Styles", "Traditional"},
            {"Styles", "Shag"},
            {"Styles", "Classic"},
            {"Styles", "Rustic"},
            {"Styles", "Scandinavian"},
            {"Styles", "Mediterranean"},
            {"Styles", "Tropical"},
*/
            {"Materials", "Wool"},
/*
            {"Materials", "Natural Fiber"},
            {"Materials", "Cotton"},
            {"Materials", "Jute"},
            {"Materials", "Silk"},
            {"Materials", "Cashmere"},
            {"Materials", "Leather"},
*/
            {"Brands", "Safavieh"},
/*
            {"Brands", "Nuloom"},
            {"Brands", "Style Haven"},
            {"Brands", "Tommy Bahama"},
            {"Brands", "Bombay Home"},
            {"Brands", "Kavka Designs"},
            {"Brands", "Abbyson"},
            {"Brands", "Kerala Weavers"},
            {"Brands", "Signature Design by Ashley"},
            {"Brands", "Passion"},
            {"Rug Types", "Outdoor"},
            {"Rug Types", "Indoor"},
            {"Rug Types", "Anti Fatigue"},
            {"Rug Types", "Patterns"},
            {"Rug Types", "Solid"},
            {"Rug Types", "Geometric"},
            {"Rug Types", "Abstract"},
            {"Rug Types", "Floral"},
            {"Rug Types", "Ornamental"},
            {"Rug Types", "Stripe"},
            {"Rug Types", "Nature"},
            {"Rug Types", "Check"},
            {"Product Features", "Handmade"},
            {"Product Features", "Stain Resistant"},
            {"Product Features", "Cushioned"},
            {"Product Features", "Non Slip"},
            {"Product Features", "Antimicrobial"},
            {"Product Features", "Eco-Friendly"},
            {"Product Features", "Reversible"},
            {"Product Features", "Made To Order"},
            {"Pile Heights", "0.25 - 0.5 inch"},
            {"Quality Ratings", "Premium Choice"},
            {"Quality Ratings", "Quality Find"},
            {"Quality Ratings", "Smart Buy"},
*/
            {"Avg. Customer Reviews", "4 & up"}
/*
            {"Avg. Customer Reviews", "3 & up"},
            {"Avg. Customer Reviews", "2 & up"},
            {"Avg. Customer Reviews", "1 & up"}
*/
        };
    }


}
