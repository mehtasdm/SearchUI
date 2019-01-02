package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Related Searches Link at the Top above the Top Nav
 * Related Searches Taxonomy at the bottom
 * SUI-633
 * SUI-1162
 *
 * Search for pink | basketball | soccer
 * There can be a maximum of 8 Related Searches pills
 * For desktop they should be at the top below "Showing most popular results for your search. Click here  to show all results."
 * For Tablet & Mobile it should be at the bottom, above Questionnaire and "Showing most popular results for your search. Click here  to show all results."
 *
 */

public class RelatedSearches {

    WebDriver driver;
    //
    @FindBy(css = "span.related-searches-heading")
    WebElement relatedSearchesHeading;                  // Related Searches:
    @FindBy(css = "a.related-search-link")
    List<WebElement> relatedSearchesLinks;              // basketball hoop | basketball goal | basketball shoes
    @FindBy(css = "div.psearch-message")
    WebElement pSearchMessage;                          // Showing most popular results for your search. Click here  to show all results.
    //
    public RelatedSearches(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public String getRelatedSearchHeading() {
        return relatedSearchesHeading.getText();
    }
    //
    public int lenghtRelatedSearches() {
        return relatedSearchesLinks.size();
    }
    //
    public void clickRelatedSearchesLinks(int iIndex) {
        int i=0;
        for (WebElement werelatedSearchesLinks : relatedSearchesLinks) {
            if (i==iIndex) {
                werelatedSearchesLinks.click();
                break;
            }
            i++;
        }
    }
    //
}
