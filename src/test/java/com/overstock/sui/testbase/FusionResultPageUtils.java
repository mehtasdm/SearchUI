package com.overstock.sui.testbase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FusionResultPageUtils {
    //
    WebDriver driver;
    //
    @FindBy(css = "input#search-input")
    WebElement inputSearchBox;
    @FindBy(css = "fieldset.submit")
    WebElement searchSubmitButton;
    //
    @FindBy(css = "a.previous")
    WebElement suiPreviousPage;
    @FindBy(css = "a.next")
    WebElement suiNextPage;
//    By suiPreviousPage = By.cssSelector("a.previous");
//    By suiNextPage = By.cssSelector("a.next");
    //
    public FusionResultPageUtils(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public String getReviewsStars(WebDriver driver, WebElement weElement) {
//        return driver.findElement(weElement).getText();
        return "";
    }
    //
    public void setKeywordSearch(String strSearch) {
        inputSearchBox.sendKeys(strSearch);
        searchSubmitButton.click();
    }
    //
    public void clickNextPage() {
        suiNextPage.click();
    }
    //
    public void clickPreviousPage() {
        suiPreviousPage.click();
    }
    //
    public boolean verifyPreviousPageEnabled() throws NoSuchElementException {
        try {
            if (suiPreviousPage.isEnabled()) {
                return true;
            } else return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    //
    // use positive location number to scroll Down (scrollY)
    // use negative location number to scroll Up (scrollY)
    public void mouseScroll(int position) throws InterruptedException {
//        String scrollPosition = "window.scrollBy(0," + Integer.toString(position)+")";
//        scroll.executeScript(scrollPosition,"");
        String strScrollToPosition = "window.scrollBy(0," + Integer.toString(position)+")";
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
//        scroll.executeScript("window.scrollBy(0,1000)","");
        scroll.executeScript(strScrollToPosition,"");
        Thread.sleep(2000);
//        scroll.executeScript("window.scrollBy(0,-500)","");
//        Thread.sleep(2000);
    }
    //
    public void pageScrollBottomOfPage() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript(("window.scrollBy(0,document.body.scrollHeight)"),"");
    }
    //
    // The method will return true, if you at the top of the page
    public boolean pageTop() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        Long value = (Long) scroll.executeScript(("return window.pageYOffset;"));
        return (value==0);
    }
    //
    public static void readExcelData(String fileName) throws IOException {
        File excelFile = new File(fileName);
        FileInputStream ofs = new FileInputStream(excelFile);
    }
}
