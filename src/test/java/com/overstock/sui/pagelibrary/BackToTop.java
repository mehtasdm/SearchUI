package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BackToTop {
    //
    WebDriver driver;

    //
    @FindBy(css = "span.buttonText")
    public WebElement backToTop;
    //
    public BackToTop(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean backToTopVisible() {
        if (backToTop.isDisplayed()) {
            return backToTop.isDisplayed();
        }
        else return false;
    }
    //
    public void setBackToTopToBeVisible() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("arguments[0].scrollIntoView();", backToTop);      // JS method scrollIntoView() to scroll the page to that web element until it becomes visible
    }
    public void clickBackToTop() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("arguments[0].scrollIntoView();", backToTop);      // JS method scrollIntoView() to scroll the page to that web element until it becomes visible
        backToTop.click();
    }
}
