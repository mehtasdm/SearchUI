package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;


/**
 * Created by Snehal on 4/11/2017.
 */
public class HeaderSearch {
    private String searchStr = "Red Rugs";
/*
    @Test
    public void headerSearch() throws Exception {
        driver.get(baseUrl);
        driver.navigate().refresh();
        By headerSearchInput = By.cssSelector("#search-input");  // Header Search Text box
        By headerSearchButton = By.cssSelector("label.os-button.btn-sm.btn-strongnav");  // Header Search Button
        //
        driver.findElement(headerSearchInput).sendKeys(searchStr);
        driver.findElement(headerSearchButton).click(); // Click the Search button
        WebDriverWait wait = new WebDriverWait(driver,20);
        // Search : Result
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[1]/ul/li[2]/span")));   // breadcrumb display
        assertTrue("This search should be on Header", driver.getCurrentUrl().contains("searchtype=Header"));
        assertTrue("Breadcrumb " + searchStr + " not found", driver.findElement(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[1]/ul/li[2]/span")).getText().contains(searchStr));  // breadcrumb text
        assertTrue("Search Nav H1 " + searchStr + " not found", driver.findElement(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[1]/ul/li[2]/span")).getText().contains(searchStr));  // Search Nav H1 text

//        System.out.println(driver.findElement(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[1]/ul/li[2]/span")).getText());  // Breadcrumb display
 /*
        assertEquals("Search: " + searchStr + " not found", "Search: " + searchStr, driver.findElement(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[1]/ul/li[2]/span")).getText());
        if (!driver.findElement(By.xpath("//*[@id=\"sn-wrapper\"]/div/div[2]/div[2]/div/div[1]/h1")).getText().contains(searchStr)) {
            System.out.println(searchStr + " not Found");
        }
        String newUrl = driver.getCurrentUrl();
        System.out.println(newUrl);
        assertEquals("Search word not present in Url", searchStr, newUrl.contains(searchStr));

    }
*/
}
