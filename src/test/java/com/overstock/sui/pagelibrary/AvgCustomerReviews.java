package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AvgCustomerReviews {

    WebDriver driver;
    //
    @FindBy(css = "h2.refinement-heading")
    List<WebElement> leftNavRefinementHeadings;                         // Should return list of Left Nav Refinements --- Price | Brands | Avg. Customer Ratings
    @FindBy(css = "span#avg\\.-customer-reviews")
    WebElement avgCustomerReviews;                                      // Avg. Customer Reviews
    @FindBy(css = "span#avg\\.-customer-reviews .refinement-item")      // Using escape sequence for . in the css span#avg.-customer-reviews
    List<WebElement> avgCustomerReviewsList;                            // 4 & up | 3 & up | 2 & up | 1 & up
    @FindBy(css = "span.click-to-restriction")                          // List of Restrictions applied & can be un-applied or removed
    List<WebElement> unSelectRestrictionsList;                          // Average Review Ratings: 4+ | Price: $xx - $xx .... Clear All
    @FindBy(css = "span#avg\\.-customer-reviews .refinement-item a.refinement-link.single-select.selected")
    WebElement closeXForAvgCustomerReviews;                             // [x] to close the Average Customer Reviews selection
    //
    // https://www.overstock.com/Bedding-Bath/Memory-Foam-Mattress-Toppers/Flat-Topper,/type,/4545/cat.html?TID=MFMTop:00:LeftNav-3:Flat

    public AvgCustomerReviews(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean foundAvgCustomerReviewsText(String avgCustText) {
        boolean bPass = false;
        for (WebElement weleftNavRefinementHeadings : leftNavRefinementHeadings) {
            if (weleftNavRefinementHeadings.getAttribute("innerHTML").contains(avgCustText)) {
                String ll = weleftNavRefinementHeadings.getAttribute("innerHTML");
                bPass=true;
            }
        }
        return bPass;
    }
    //
    public boolean expandAvgCustomerReviews(String avgCustText) throws InterruptedException {
        boolean bPass=false;
        for (int i = 0; i < leftNavRefinementHeadings.size(); i++) {
            if (leftNavRefinementHeadings.get(i).getAttribute("innerHTML").contains(avgCustText)) {
//                if (leftNavRefinementHeadings.get(i).getAttribute("class").contains("closed")) {
                    leftNavRefinementHeadings.get(i).click();              // Expand Avg. Customer Review Refinement when it is collapsed
                Thread.sleep(300);
                if (!avgCustomerReviews.getAttribute("class").contains("closed")) {
                    bPass=true;
                }
//                }
            }
        }
        return bPass;
    }
    //
    public int countAvgCustomerReview() {
        return avgCustomerReviewsList.size();
    }
    //
    public void clickAvgCustomerReviews(int iIndex) {
        for (int i=0; i < avgCustomerReviewsList.size(); i++) {
            if (i==iIndex) {
                avgCustomerReviewsList.get(i).click();
            }
        }
    }
    //
    public String textAvgCustomerReviewList(int iIndex) {
        for (int i=0; i<avgCustomerReviewsList.size();i++) {
            if (i==iIndex) {
                return avgCustomerReviewsList.get(i).getAttribute("innerHTML");
            }
        }
        return null;
    }
}
