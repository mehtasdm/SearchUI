package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by smehta on 5/5/2017.
 * Search for product which does not exists in overstock database
 * Verify "Your search: keyword returned no results."
 * Verfiy it displays carousel of Recommended for You products
 * Verify it displays carousel of Overstock.com Top Sellers
 * Left Nav should not be displayed
 */
public class NoResultsPage {
    WebDriver driver;
    //
    @FindBy(css = "div.whoops.col-lg-12 > h1")              // Your search: "Kittaabb"returned no results.
    WebElement returnedNoResults;
    @FindBy(css = ".rec-for-you.rec-header")                // Recommended for You
    WebElement recommendedForYou;
    @FindBy(css = ".top-sellers.rec-header")                // Overstock.com Top Sellers
    WebElement topSellers;
    //
    public NoResultsPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public String getNoResultsReturned() {
        return returnedNoResults.getText();
    }
    //
    public String getRecommendedForYou() {
        return recommendedForYou.getText();
    }
    //
    public String getTopSellers() {
        return topSellers.getText();
    }
    //
}