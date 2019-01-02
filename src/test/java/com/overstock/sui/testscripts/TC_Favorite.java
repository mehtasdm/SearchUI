package com.overstock.sui.testscripts;

import com.overstock.sui.pagelibrary.Favorite;
import com.overstock.sui.testbase.ExcelReader;
import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Search for sofas and couches
 * On the Result page, heart icon should be displayed
 * Click on the heart button
 * Verify Favorite modal window should open
 * Verify Favorite is checked
 * Verify Favorite heart for the product is Red (data-is-favorited="true")
 * Again on the same product, click on the Red heart icon
 * Verify Favorite modal window opens
 * Verify Favorite is unchecked
 * Verify Favorite heart for the product is not Red (data-is-favorited="false")
 * Save For Later
 * Create New List
 *
*/

public class TC_Favorite extends TestBase {
    //
    Favorite favorite;
    ExcelReader excelReader;
    String testUrl;
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    int tileNumber=0;
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
        favorite=new Favorite(driver);
        excelReader = new ExcelReader(excelFileName);
        testUrl = excelReader.randomUrl();
        driver.get(testUrl);
    }
    //
    @Test(priority = 0, enabled = true)
    public void setFavorite() {
        String mainWindow = driver.getWindowHandle();
        favorite.setFavorite(tileNumber, "MakeFavorite");
        driver.findElement(By.cssSelector(".search-nav-h1")).click();

        //
        Set<String> pops = driver.getWindowHandles();
        Iterator<String> it = pops.iterator();
        while (it.hasNext()) {
            String popupHandle = it.next();
            if (!mainWindow.equals(popupHandle)) {
                driver.switchTo().defaultContent();
//                driver.switchTo().window(popupHandle);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);

        //        if (!favorite.verifyFavoriteSelected(tileNumber, 1)) {
//        if (!favorite.isFavorite(tileNumber)) {
//            logger.error("*** Not able to Make Favorite for this tile ***");
//        }
    }
    //
    @Test(priority = 1, enabled = false)
    public void removeFavorite() {
        if (favorite.setFavorite(tileNumber, "RemoveFavorite")) {
            logger.error("*** Not able to remove Favorite for this tile ***");
        }
    }
    //
    @Test(priority = 2, enabled = false)
    public void saveForLater() {
        favorite.saveForLater(tileNumber);
        if (!favorite.verifyFavoriteSelected(tileNumber, 1)) {
            logger.error("*** Save For Later not selected ***");
        }
    }
    //
}
