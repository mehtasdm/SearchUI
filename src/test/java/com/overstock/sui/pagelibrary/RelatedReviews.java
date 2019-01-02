package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RelatedReviews {
    WebDriver driver;
    //
    @FindBy(css = "h2.section-title")
    WebElement relatedReviewsSectionTitle;
//    @FindBy(css = ".review-image > a > img")
//    WebElement relatedReviewsImage;
    @FindBy(css = "h3.title")
    WebElement relatedReviewsTitle;
    @FindBy(css = ".review-info > div.reviews")
    WebElement relatedReviewsStars;
    @FindBy(css = ".review-info > p.text")
    WebElement relatedReviewsText;
    @FindBy(css = "div.arrow.left")
    WebElement relatedReviewsArrowLeft;
    @FindBy(css = "div.arrow.right")
    WebElement relatedReviewsArrowRight;
    @FindBy(css = "div.read-more")
    private List<WebElement> relatedReviewsReadMore;
    @FindBy(css = ".review-image > a > img")
    private List<WebElement> relatedReviewsImages;
    /*
    @FindBy(css = "div.read-more")
    WebElement relatedReviewsReadMore;
    By relatedReviewsReadMore = By.cssSelector("div.read-more");
    By relatedReviewsImages = By.cssSelector(".review-image > a > img");
    By relatedReviewsSectionTitle = By.cssSelector("h2.section-title");
    By relatedReviewsImage = By.cssSelector(".review-image > a > img");
    By relatedReviewsTitle = By.cssSelector("h3.title");
    By relatedReviewsStars = By.cssSelector(".review-info > div.reviews");
    By relatedReviewsText = By.cssSelector(".review-info > p.text");
    By relatedReviewsArrowLeft = By.cssSelector("div.arrow.left");
    By relatedReviewsArrowRight = By.cssSelector("div.arrow.right");
    By relatedReviewsReadMore = By.cssSelector("div.read-more");
//    By relatedReviewsPreviousPage = By.cssSelector("a.previous");
//    By relatedReviewNextPage = By.cssSelector("a.next");
*/
    //
    public RelatedReviews (WebDriver driver)
    {
        this.driver = driver;
    }
    //
    public String getRelatedReviewsSectionTitle() {
//        System.out.println(relatedReviewsSectionTitle.getText());
        return relatedReviewsSectionTitle.getText();
    }
    //
    public void clickRelatedReviewsImage() {
//        List<WebElement> weRelatedReviewsImages = driver.findElements(relatedReviewsImages);
        List<WebElement> weRelatedReviewsImages = relatedReviewsImages;
        for (WebElement weRelatedReviewsImg: weRelatedReviewsImages) {
            weRelatedReviewsImg.click();
            break;
        }
//        driver.findElement(relatedReviewsImage).click();
//        return;
    }
    //
    public String getRelatedReviewsTitle() {
//        System.out.println(relatedReviewsTitle.getText());
        return relatedReviewsTitle.getText();
    }
    //
    public String getRelatedReviewsStars() {
        return relatedReviewsStars.getText();
    }
    //
    public String getRelatedReviewsText() {
        return relatedReviewsText.getText();
    }
    //
    public boolean clickRelatedReviewsReadMore() {
        List<WebElement> lweRelatedReviewsReadMore = relatedReviewsReadMore;
//        List<WebElement> lweRelatedReviewsReadMore = driver.findElements(relatedReviewsReadMore);
        if (lweRelatedReviewsReadMore.size()==0) {
            return false;
        }
        for (WebElement weRRReadMore : lweRelatedReviewsReadMore) {
            weRRReadMore.click();
            break;
        }
        return true;
    }
    //
    public void clickRelatedReviewsArrowRight() {
        relatedReviewsArrowRight.click();
    }
    //
    public void clickRelatedReviewsArrowLeft() {
        relatedReviewsArrowLeft.click();
    }
    //
    /*
    public void clickRelatedReviewsNextPage() {
        driver.findElement(relatedReviewNextPage).click();
    }
    //
    public void clickRelatedReviewsPreviousPage() {
        driver.findElement(relatedReviewsPreviousPage).click();
    }
    */
    //
}
