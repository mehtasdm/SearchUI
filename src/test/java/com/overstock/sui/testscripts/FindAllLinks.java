package com.overstock.sui.testscripts;

import com.overstock.sui.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FindAllLinks extends TestBase {
    private String testUrlNavigation = "https://www.overstock.com/Home-Garden/Chandeliers-Pendants/34/subcat.html";
    private String testUrlSearch = "https://www.overstock.com/search?keywords=kids+toys&taxonomy=sto5&ralt=sto35,sto34,sto9&TID=AR:TRUE&searchtype=Header";
    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    //
    //
    @BeforeTest
    public void setUp() throws IOException {
        init();
    }
    //
    @Test(priority = 0, enabled = false)
    public void sortByNavigationDummy() {
        // this test is just to set the url
        driver.get(testUrlNavigation);
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for (WebElement link : allLinks ) {
            System.out.println(link.getAttribute("href") + " --- "  + link.getText());
        }
    }
    //
    @Test(priority = 1, enabled = true)
    public void sortByNavigationDummy2() {
        // this test is just to set the url
        driver.get(testUrlNavigation);
        List<WebElement> parent = driver.findElements(By.cssSelector("main.nl2"));
        for (WebElement child : parent) {
            List<WebElement> allLinks = child.findElements(By.tagName("a"));
            for (WebElement link : allLinks) {
                System.out.println(link.getAttribute("href") + " --- " + link.getText());
            }
        }
    }
}
