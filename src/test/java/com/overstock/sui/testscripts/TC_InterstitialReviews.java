package com.overstock.sui.testscripts;

import com.overstock.sui.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by smehta on 6/26/2017.
 * SUI-491 / SUI-548
 * Verify that Interstitial Reviews are displayed after every 6 tile rows
 * Instead of showing all 5 rows on the first page, we are showing two rows--one row of reviews around the middle, and another row at the end
 * 5 rows of reviews spread over the first 3 pages
 * It will only show two on page 1 and two on page 2, and 1 on page 3
 * It'll show as many reviews as we get (up to five rows max).
 * and spread the 5 rows across as many pages of results we get.
 * If there is no page 3, then the user doesn't see the last row
 * if there is no page 2, then the user only see 4 reviews
 *
 */
public class TC_InterstitialReviews extends TestBase {
    private String searchStr = "Red Rugs";
/*
    @Test
    public void interstitialReviews() throws Exception {
        driver.get(baseUrl);
        driver.navigate().refresh();
        By headerSearchInput = By.cssSelector("#search-input");  // Header Search Text box
        By headerSearchButton = By.cssSelector("label.os-button.btn-sm.btn-strongnav");  // Header Search Button
        //
        driver.findElement(headerSearchInput).sendKeys(searchStr);
        driver.findElement(headerSearchButton).click();  // Click the Search button
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".feedback-wrapper")));  // Feed back section is displayed
        //
        List<WebElement> weReviewsTileLists = driver.findElements(By.cssSelector("a.product-name"));   // Review tiles / Review rows / Review info
        Assert.assertTrue("Reviews not found",weReviewsTileLists.size()==4);  // first page should display 2 rows of 2 tiles each of Reviews
        for (WebElement weReview : weReviewsTileLists) {
            // verify if Review is clickable
            JavascriptExecutor js = (JavascriptExecutor)driver;
            System.out.println("\"windows.scrollTo(0,\" + weReview.getLocation().y + \")\")");
            js.executeScript("windows.scrollTo(0," + weReview.getLocation().y + ")");
//          weReview.click();
        }
    }
        // Search : Result*/
}
