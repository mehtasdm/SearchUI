package com.overstock.sui.pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.util.Strings;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by smehta on 5/3/2017.
 * BSD-369-pSrch-Feature Product
 * Assert image click
 * Assert Product Name Title click
 * Assert Product Review click
 * Assert Product Review count click
 * Assert selection of Options
 * Assert selection of Quantity
 * Assert Add to Cart
 * Assert Back Button click
 * Assert Top Nav is displayed
 * "Go to Featured product result page with below url:-
 *
 * https://www.overstock.com/search?keywords=red+shoes&taxonomy=sto7&ralt=sto6,sto33,sto1&TID=AR:TRUE&searchtype=Header&featuredproduct=7333212&featuredoption=9926807"  -- User should see the featured product at top.
 * Verify that click on image takes user to product detail page. -- User should be taken to product detail page.
 * Verify that click on product name takes user to product detail page. -- User should be taken to product detail page.
 * Verify that user can choose the required option from drop down. -- Users should be able to choose the required option from drop down.
 * Verify that user can choose the required Quantity -- Users should be able to choose the required Quantity
 * Click on "Add to cart" button -- Product should be added to cart with correct price.
 * Click on back button -- User should be taken to product title page and featured product should be displayed at top.
 * Verify for featured products with no options
 * Verify for featured products out of stock
 * Featured Products with Free Shipping
 * Featured Products with no Free Shipping
 */
public class FeaturedProduct {
    //
    WebDriver driver;
    //
    @FindBy(css = "div.featured-product-image.remove-padding")
    WebElement featuredProductImg;
    @FindBy(css = "div.product-title")
    List<WebElement> featuredProductTitle;
    @FindBy(css = "span.from-price")
    List<WebElement> featuredProductPrice;
    @FindBy(css = "div.reviews")
    List<WebElement> featuredProductReview;
    @FindBy(css = "span.product-review")
    List<WebElement> featuredProductReviewcount;
    @FindBy(css = "#featured-options")
    WebElement featuredProductOptions;
    @FindBy(css = "select#featured-quantity.featured-quantity")
    WebElement featuredProductQty;
    @FindBy(css = "input.featured-add-button")
    WebElement featuredProductAddToCart;
    @FindBy(css = "span.confirmation-message")
    WebElement featuredProductAddToMessage;
    @FindBy(css = "input.featured-add-button")
    WebElement featuredProductCheckOutNow;
    @FindBy(css = "span.confirmation-message")
    WebElement featuredProductConfMsg;
    @FindBy(css = "div.sort-by-text")
    WebElement topNavSortMenuHover;
    @FindBy(css = "li.sort-by-option")
    List<WebElement> topNavSortBy;
    @FindBy(css = "div.free-shipping-wrapper.desktop > div.free-shipping-text")
    WebElement freeShipping;
    //
    public FeaturedProduct(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean featuredProductImgClick() {
        try {
            featuredProductImg.click();
            return (driver.getCurrentUrl().contains("product.html"));
        }
        catch (Exception e) {
            return false;
        }
    }
    //
    public boolean featuredProductTitleClick() {
        for (int i = 0; i < featuredProductTitle.size(); i++) {
            featuredProductTitle.get(i).click();
            break;
        }
        return (driver.getCurrentUrl().contains("product.html"));
    }
    //
    public boolean featuredProductReviewClick() {
        try {
            for (int i=0; i < featuredProductReview.size(); i++) {
                featuredProductReview.get(i).click();
                break;
            }
            return (driver.getCurrentUrl().contains("product.html"));
        }
        catch (Exception e) {
            return false;
        }
    }
    //
    public boolean featuredProductReviewCountClick() {
        try {
            for (int i=0; i < featuredProductReviewcount.size(); i++) {
                featuredProductReviewcount.get(i).click();
                break;
            }
            return (driver.getCurrentUrl().contains("product.html"));
        }
        catch (Exception e) {
            return false;
        }
    }
    //
    public String getFeaturedProductPrice() throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        String fpPrice=null;
        for (int i=0; i < featuredProductPrice.size(); i++) {
            fpPrice = featuredProductPrice.get(i).getText();
            break;
        }
        return fpPrice;
    }
    //
    public boolean featuredProductFreeShipping() throws NoSuchElementException {
        return freeShipping.getText().equalsIgnoreCase("free shipping");
    }
    //
    public void setFeaturedProductQty(int Index) throws NoSuchElementException {
        Select selectFeaturedProductQty = new Select(featuredProductQty);
        selectFeaturedProductQty.selectByIndex(Index);
    }
    //
    public void setFeaturedProductOptions(int Index) throws NoSuchElementException {
        Select selectFeaturedProductOptions = new Select(featuredProductOptions);
        selectFeaturedProductOptions.selectByIndex(Index);
//        Thread.sleep(300);
//        return true;
    }
    //
    public void featuredProductAddToCartClick() throws NoSuchElementException {
        featuredProductAddToCart.click();
    }
    //
    public boolean featuredProductAddToCartMessage() throws NoSuchElementException {
        return (featuredProductAddToMessage.getText().equals("This item has been added to your cart"));
    }

    //
    public boolean featuredProductCheckoutNowClick() throws NoSuchElementException {
        featuredProductCheckOutNow.click();
        return (driver.getCurrentUrl().contains("https://www.overstock.com/checkout"));
    }
    //
    public boolean topNavIsClickable() throws InterruptedException, NoSuchElementException {
        Actions actions = new Actions(driver);
        actions.moveToElement(topNavSortMenuHover).build().perform();
        for (int i=0; i < topNavSortBy.size(); i++) {
            actions.click(topNavSortBy.get(i)).build().perform();
            break;
        }
        return (driver.getCurrentUrl().contains("sort"));
    }
    //
    public void featuredProductBackButton() throws InterruptedException {
        driver.navigate().back();
    }
    //
}
