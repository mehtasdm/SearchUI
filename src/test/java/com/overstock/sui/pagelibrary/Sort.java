package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by smehta on 4/10/2017.
 * Hover over Sort by
 * Sort by last element from the list
 * Verify that the baseUrl and sorted Url are different
 */
public class Sort {
    WebDriver driver;
    //
//    @FindBy(css = ".sort-by-text")
    @FindBy(css = ".sort-by")
    WebElement sortMenuHover;
//    @FindBy(css = "li.sort-by-option")
    @FindBy(css = "select.sort-by-ul")
    WebElement sortBy;
    @FindBy(css = "select.sort-by-ul option")
    WebElement sortByOption;
    //
    public Sort(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public void hoverSortBy() {
        Actions actions = new Actions(driver);
        actions.moveToElement(sortMenuHover).build().perform();
    }
    //
    public void setSortByDropdown(String sortByDropdown) throws InterruptedException {

        sortBy.click();

//        Thread.sleep(500);
//        driver.findElement(By.linkText(sortByDropdown)).click();
        Select selectSortByDropdown = new Select(sortBy);
        //Select selectSortByDropdown = new Select(driver.findElement(By.cssSelector("select.sort-by-ul")));
        selectSortByDropdown.selectByIndex(2);
//        selectSortByDropdown.selectByVisibleText(sortByDropdown);
        Thread.sleep(5000);
    }
    //
}
