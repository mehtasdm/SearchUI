package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.right;

/**
 * Created by smehta on 4/19/2017.
 * BSD-366-Product Search-Search Term Decomposition
 * Verify below sections are displayed with Search Term Decomp
 * Search Term Decomp
 * Recommended for You
 * Overstock.com Top Seller
 */
public class SearchTermDecomp {
    WebDriver driver;
    //
    @FindBy(css = "div.whoops.col-lg-12")
    WebElement noResults;                           // Your search: "park bridgette duvet toys" returned no results.
    @FindBy(css = "h1.search-nav-h1")
    WebElement h1SearchNav;                         // Park Bridgette Duvet Toys
    @FindBy(css = "div.breadcrumb-container")
    WebElement breadCrumbContainer;                 // Search: park bridgette duvet toys 1-1 of 1 Results
    @FindBy(css = "p.search-term-results")
    List<WebElement> searchTermDecomp;                    // 5  Results for "park bridgette",  // 2  Results for "duvet toys"  ....
    @FindBy(css = "a.search-term-see-all")
    List<WebElement> seeAll;
    //
    public SearchTermDecomp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean verifyNoResults() {
        return (noResults.getText().toLowerCase().contains("returned no results") || noResults.getText().contains("Search: "));
    }
    //
    public boolean verifyh1SearchNav() {
        Integer s = h1SearchNav.getText().lastIndexOf("-");
//        System.out.println(s);
//        System.out.println(Integer.parseInt(right(h1SearchNav.getText(), s)));
        return (Integer.parseInt(right(h1SearchNav.getText(), s)) < 25);
    }
    //
    public boolean verifyBreadCrumbContainer() {
        return (breadCrumbContainer.getText().contains(" Results"));
    }
    //
    public Integer verifySearchTermDecomp() throws InterruptedException {
//        Thread.sleep(500);
//        List<WebElement> lweSearchTerms = driver.findElements(By.cssSelector("p.search-term-results"));
        return searchTermDecomp.size();
    }
    //
    public Integer clickSeeAll() {
//        List<WebElement> lweSeeAll = driver.findElements(By.cssSelector("a.search-term-see-all"));
        for (WebElement weSeeAll : seeAll) {
            weSeeAll.click();
            break;
        }
        return seeAll.size();
    }
    //
    // http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/
    public void PatternNMatcher(String inputStr,  String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find()) {
            System.out.println("found : " + matcher.group());
        }
    }
    //
}