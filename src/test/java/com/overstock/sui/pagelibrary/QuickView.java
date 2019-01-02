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
**/
public class QuickView  {
    WebDriver driver;
    //
    @FindBy(css = "span.quick-view-text")
    private List<WebElement> qvQuickViewButton;
    @FindBy(css = "div.product-tile")
    private List<WebElement> qvProductTile;
    @FindBy(css = "h2.product-title")
    private WebElement qvProductTitle;
    @FindBy(xpath = "//*[@id=\"1\"]/div[6]/div[1]/div[3]/div[1]/a/div/div/div/img")
    private WebElement qvReviewStars;
    @FindBy(xpath = "//*[@id=\"1\"]/div[6]/div[1]/div[3]/div[1]/a/div/div/span")
    private WebElement qvReviewCount;
    @FindBy(css = "div.quick-view-container > div.quick-view-wrapper > div.qv-product-image > a > img")
    private WebElement qvImage;
    @FindBy(xpath = ".//*[@id='1']/div[6]/div[1]/div[3]/div[2]")
    private WebElement qvProductDesc;
    @FindBy(xpath = ".//*[@id='1']/div[6]/div[1]/div[3]/div[3]/div")
    private List<WebElement> qvPrice;
//    @FindBy(css = ".product-options .quantity")
    @FindBy(css = "select.quantity")
    private WebElement qvQty;
    @FindBy(css = "select.options")
    //    @FindBy(css = ".product-options .options")
    private WebElement qvOptions;
    @FindBy(css = "div.sold-out")
    private WebElement qvSoldOut;                           // "This item is out of stock"
    @FindBy(css = ".add-to-cart.quick-view-btn-group")
    private WebElement qvAddToCart;                         // Add to Cart or "Out Of Stock"
    @FindBy(css = ".add-to-cart.quick-view-btn-group")
    private WebElement qvCheckoutNow;
    @FindBy(css = ".quick-view-checkout-message")
    private WebElement qvCheckoutNowMessage;
    @FindBy(css = ".check-out-now.quick-view-btn-group")
    private WebElement qvExpressCheckout;
    @FindBy(css = ".quickview-thumbnail-image")
    private WebElement qvThumbnailImage;
    @FindBy(css = "div.recommended-product img")
    private List<WebElement> qvSimilarItems;
    @FindBy(css = "div.recommended-product img")
    private List<WebElement> qvSimilarItem;
    @FindBy(css = "svg.icon-close")
    private WebElement qvIconClose;
    //
    public QuickView (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public void quickViewClick (WebDriver driver) throws InterruptedException {

        List<WebElement> lweProductTiles =  qvProductTile;
        Actions actions = new Actions(driver);
        for (WebElement weProdTile : lweProductTiles) {
            // click the first product tile
            actions.moveToElement(weProdTile).perform();
            break;
        }
//        Thread.sleep(300);
//        List<WebElement> lweQuickViews = driver.findElements(By.cssSelector(".interstitial-quick-view.os-button.btn-sm.btn-action"));
//        List<WebElement> lweQuickViews = driver.findElements(By.cssSelector("span.quick-view-text"));
        List<WebElement> lweQuickViews = qvQuickViewButton;
        for (WebElement weQuickView : lweQuickViews) {
            actions.click(weQuickView).build().perform();
            break;
        }
    }
    //
    public void clickQuickViewProductTitle() {
        qvProductTitle.click();
    }
    //
    public void clickQuickViewReviewStars() {
        qvReviewStars.click();
    }
    //
    public void clickQuickViewReviewCount() {
        qvReviewCount.click();
    }
    //
    public void clickQuickViewImage() {
        qvImage.click();
    }
    //
    public boolean getQuickViewProductDesc() {
        return !Strings.isNullOrEmpty(qvProductDesc.getText());
//        return qvProductDesc.getText() != null;
    }
    //
    public boolean getQuickViewPrice() {
        List<WebElement> lweQVPrices = qvPrice;
        for (WebElement weQVPrice : lweQVPrices) {
            return !Strings.isNullOrEmpty(weQVPrice.getText());
//            return (weQVPrice.getText() !=null);
        }
        return false;
    }
    //
    public boolean setQuickViewQty(int Index) throws InterruptedException {
        Select selectQuickViewQty = new Select(qvQty);
        selectQuickViewQty.selectByIndex(Index);
//        Thread.sleep(5000);
        return true;
    }
    //
    public boolean setQuickViewOptions(int Index) throws InterruptedException {
        Select selectQuickViewOptions = new Select(qvOptions);
        selectQuickViewOptions.selectByIndex(Index);
//        Thread.sleep(300);
        return true;
    }
    //
    public void clickQuickViewAddToCart() {
        qvAddToCart.click();
    }
    //
    public boolean getQuickViewAddToCartMessage() {
        return qvCheckoutNowMessage.getText().contains("Item has been added to cart");
    }
    //
    public void clickQuickViewCheckoutNow() {
        qvCheckoutNow.click();
    }
    //
    public void clickQuickViewExpressCheckout() {
        qvExpressCheckout.click();
    }
    //
    public boolean clickQuickViewThumbnailImage() throws InterruptedException {
        String qvImageBefore = qvImage.getTagName();
        qvThumbnailImage.click();
//        Thread.sleep(300);
        String qvImageAfter = qvImage.getTagName();
        return (qvImageAfter.equals(qvImageBefore));
    }
    //
    public boolean getQuickViewSimilarItems() throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        return (qvSimilarItems.size()==0);
    }
    //
    public boolean clickQuickViewSimilarItemsCarousel() throws InterruptedException {
        String qvImageBefore = qvImage.getTagName();
        qvSimilarItem.clear();
//        Thread.sleep(300);
        String qvImageAfter = qvImage.getTagName();
        return (qvImageAfter.equals(qvImageBefore));
    }
    //
    public void clickQuickViewIconClose() {
        qvIconClose.click();
    }
}
