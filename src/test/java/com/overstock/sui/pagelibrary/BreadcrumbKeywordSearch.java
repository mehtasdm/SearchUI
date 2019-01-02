package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Snehal on 1/16/2018.
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
public class BreadcrumbKeywordSearch {
    WebDriver driver;
    //
    @FindBy(css = "h1.search-nav-h1")               // Your Search Results for "Red Shoes"
    WebElement searchHeaderH1;
    @FindBy(css = "a.breadcrumb-taxonomy")          // Clothing & Shoes / Shoes / Women's Shoes / Heels / Search: red shoes1-60 of 906 Results
    List<WebElement> breadCrumb;
    //
    public BreadcrumbKeywordSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public String getSearchHeaderH1() {
        return searchHeaderH1.getText();
    }
    //
    public Integer getBreadcrumbLinkSize() {
        return breadCrumb.size();
    }
    //
    public String getBreadcrumbLink(int i) {
        return breadCrumb.get(i).getText();
    }
    //
    public void clickBreadcrumbLink(Integer pos) throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        for (int i=0; i <= breadCrumb.size(); i++) {
            if (i==pos) {
                breadCrumb.get(i).click();
            }
        }
    }
    //
/*
        public void keyBreadCrumb() throws Exception {
        driver.get(baseUrl);
        driver.navigate().refresh();
        By headerSearchInput = By.cssSelector("#search-input");  // Header Search Text box
        By headerSearchButton = By.cssSelector("label.os-button.btn-sm.btn-strongnav");  // Header Search Button
        By colorRefinement = By.cssSelector("a[href*=',Red,']");    // Color Refinement
        By removeColorRefinement= By.cssSelector("[title='color'] .remove-restriction");  // Remove color Red filter
        By priceRefinement = By.cssSelector(("a[href*=',(80,),']"));  // Price Refinement
        By removePriceRefinement = By.cssSelector("[title='price'] .remove-restriction");  // Remove $80 filter
        //
        driver.findElement(headerSearchInput).sendKeys("Red Shoes");
        driver.findElement(headerSearchButton).click(); // Click the Search button
        //
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul#colors")));
        driver.findElement(colorRefinement).click();  // Color Red clicked
        // Verify that Color Red refinement is applied and appears in the Breadcrumb
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".click-to-restriction"))); // If filter Red is applied
*//*
        List<WebElement> weColorsList = driver.findElements(By.cssSelector("ul#colors"));  // find all the elements in Colors
        // Color Red checkbox should be selected
        boolean colorCheckbox = false;
        for (WebElement weColor : weColorsList) {
            System.out.println(weColor.getTagName() + " " + weColor.getClass().getName()+" "+weColor.getText());
            if (weColor.isSelected()) {
                colorCheckbox = true;
            }
        }
        System.out.println(colorCheckbox);
        Assert.assertTrue("Red filter not selected", colorCheckbox);
*//*
        driver.findElement(priceRefinement).click();  // Select $80 Price filter
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='price'] .remove-restriction"))); // $80 filter is applied
        driver.findElement(removeColorRefinement).click();  // Remove color Red filter
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='price'] .remove-restriction"))); // $80 filter is still applied
        driver.findElement(removePriceRefinement).click();  // Remove $80 filter
    }
    */
}
