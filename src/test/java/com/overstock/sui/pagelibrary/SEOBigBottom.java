package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SEOBigBottom {
    // https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html
    WebDriver driver;
    //
    @FindBy(css = "h3.refinement-heading")
    List<WebElement> leftNavRefinementHeadings;                         // Should return list of Left Nav Refinements --- Price | Brands | Avg. Customer Reviews
    @FindBy(css = "div.header-image")
    WebElement seoBigBottomImage;                                       // SEO Big Bottom Image should not be clickable
    @FindBy(css = "button.bottom-content-read-more")
    WebElement seoBigBottomReadMoreOrLessButton;
    @FindBy(css = "span#avg\\.-customer-reviews .refinement-item")      // Using escape sequence for . in the css span#avg.-customer-reviews
        List<WebElement> avgCustomerReviewsList;                            // 4 & up | 3 & up | 2 & up | 1 & up
    @FindBy(css = "span.click-to-restriction")                          // List of Restrictions applied & can be un-applied or removed
        List<WebElement> unSelectRestrictionsList;                          // Average Review Ratings: 4+ | Price: $xx - $xx .... Clear All
    @FindBy(css = "span#avg\\.-customer-reviews .refinement-item a.refinement-link.single-select.selected")
    WebElement closeXForAvgCustomerReviews;                             // [x] to close the Average Customer Reviews selection
    //
    public SEOBigBottom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // SEO Big Bottom Image should not be clickable
    public boolean displayedSEOBigBottomImage() {
        return seoBigBottomImage.isDisplayed();
    }
    //
    public void clickSEOBigBottomImage() {
        seoBigBottomImage.click();
    }
    //
    public boolean clickReadMoreButton(String strMoreOrLes) {
        // strMoreOrLes == read more || read less depending
        boolean bPass=false;
        if (seoBigBottomReadMoreOrLessButton.getAttribute("innerHTML").contentEquals(strMoreOrLes)) {
            bPass=true;
            seoBigBottomReadMoreOrLessButton.click();
        }
        return bPass;
    }
    //
}
