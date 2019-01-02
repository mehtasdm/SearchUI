package com.overstock.sui.pagelibrary;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;
//import org.testng.annotations.Test;
/**
 * Created by smehta on 6/20/2017.
 * Move Next Page
 * Move Previous Page
 * Browser Back Button
 * Move Page number wise
 */
public class Pagination {
    WebDriver driver;
    //
//    @FindBy(css = ".next-wrapper.pagination-btn>a.next")
    @FindBy(css = "a.next")
    private WebElement pageNext;
    @FindBy(css = "div.previous.disabled")
    private WebElement pagePreviousOnPageLoad;
//    @FindBy(css = ".previous-wrapper.pagination-btn>a.previous")
    @FindBy(css = "a.previous")
    private WebElement pagePrevious;
//    @FindBy(css = ".previous-wrapper.pagination-btn>div.previous")
//    @FindBy(css = "div.previous")
    @FindBy(css = ".pagination-page-number.active")
    private WebElement activePage;
    @FindBy(css = ".pagination-page-number")
    private List<WebElement> liPagination;
    //
    public Pagination(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    //
    public boolean pagePreviousOnPageLoadDisabled() {
        return pagePreviousOnPageLoad.getAttribute("class").contains("disabled");
    }
    //
    public boolean pagePreviousEnabled() {
        return pagePrevious.isEnabled();
    }
    //
    public boolean pageNextEnabled() {
        return pageNext.isEnabled();
    }
    //
    public void clickPagePrevious() {
        pagePrevious.click();
    }
    //
    public void clickPageNext() {
        pageNext.click();
    }
    //
    public boolean isPageActive(int pageNum) {
        for (int i=0; i < liPagination.size(); i++) {
            if (i==pageNum-1) {
                return liPagination.get(i).getAttribute("class").contains("active");
            }
        }
        return false;
    }
    //
    public boolean page2IsActive() {
        for (int i=0; i < liPagination.size(); i++) {
            if (i==1) {
                return liPagination.get(i).getAttribute("class").contains("active");
            }
        }
        return false;
    }
    //
    public void browserBackButon() {
        driver.navigate().back();
    }
    //
}
