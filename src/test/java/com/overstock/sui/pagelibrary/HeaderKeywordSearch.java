package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Snehal on 4/16/2017.
 * BSD-365-Bread Crumb and Result count for Keyword Search

    Go to overstock.com homepage
    Perform keyword search for term 'Red Shoes'
    Apply "Red" color filter
    Apply "$80+" price filter
    Apply "Top rated" more ways to shop filter
    Remove the $80 and Top rated bread crumbs which are present at the top
    Now remove "Red" color filter as well
    Verify that Bread crumb is removed and user is shown correct result count. Thus validate that Bread crumb and result count functionality works as required.
 */
public class HeaderKeywordSearch {
    //
    WebDriver driver;
    //
    @FindBy(css = "input#search-input.input-text")
    WebElement headerSearchInput;
    @FindBy(css = "i.os-icon-magnify.os-icon")
    WebElement headerSearchButton;
    @FindBy(css = "h1.search-nav-h1")
    WebElement headerSearchNavH1;
    /*
    By headerSearchInput = By.cssSelector("input#search-input.input-text");  // Header Search Text box
    By headerSearchButton = By.cssSelector("i.os-icon-magnify.os-icon");  // Header Search Button
    By headerSearchNavH1 = By.cssSelector("h1.search-nav-h1");
    */
    //
    public HeaderKeywordSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public void setHeaderSearchInput(String input) {
        headerSearchInput.clear();
        headerSearchInput.sendKeys(input);
        // driver.findElement(headerSearchInput).sendKeys(input);
    }
    //
    public void clickHeaderSearchButton() {
        //driver.findElement(headerSearchButton).click();
        headerSearchButton.click();
    }
    //
    public String getHeaderSearchNavH1() {
        //return driver.findElement(headerSearchNavH1).getText();
        return headerSearchNavH1.getText();
    }
    //
}
