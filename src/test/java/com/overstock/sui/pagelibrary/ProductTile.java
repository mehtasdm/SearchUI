package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Created by smehta on 12/26/2017
 * On Fusion search page, click on any of the product
 * Verify the product tile Tag on top left corner [DoorBuster / SALE / Daily Dig / Flash Deal / CLEARANCE / Liquidation / Exclusive / New Arrival / PBS KIDS / EXTRA REWARDS]
 * Verify Favorite/wishlist heart is either gray or red on top right corner
 * Verify product image
 * Verify Price Type = Sale or without Sale
 * Verify Price Range $999.99 - $9,999.99 (product with options) or just the price $999.99 (e.g. $249.99 = MSRP=$617.00 less $367.01 OFF)
 * Verify was-reference-price --- "MSRP or Was"
 * Verify data-tip "See details on product page" on hover on MSRP or Was or MSRP/Was price
 * Verify product title
 * Verify product footer viz. Reviews & Reviews Star counts

 * The product details page should be displayed
 * Click on the browser back button on product details page, the fusion search page should be displayed
 *
 */
public class ProductTile {
    //
    WebDriver driver;
    private int tileNumber=0;                               // all the validation will be performed on this tileNumber
    //
    @FindBy(css = "div.sale-only")
    private List<WebElement> productTileSaleOnly;           // product tile Tag [SALE]
    @FindBy(css = "div.wishlist-heart-container.list-creation.list-modal")
    private List<WebElement> productTileWishListHeart;
    @FindBy(css = "div.img-middle")
    private List<WebElement> productTileImg;
    @FindBy(css = "span.more-options-text")
    private List<WebDriver> productTileMoreOptionsText;     // More Options text
    @FindBy(css = "span.price-type")
    private List<WebElement> productTilePriceType;          // Sale
    @FindBy(css = "span.from-price > span.price-sign")
    private List<WebElement> productTileFromPriceSign;      // $
    @FindBy(css = "span.from-price > span.price-dollar")
    private List<WebElement> productTileFromPriceDollar;    // 2,446
    @FindBy(css = "span.from-price > span.price-cent")
    private List<WebElement> productTileFromPriceCent;      // .99
    @FindBy(css = "span.to-price > span.price-sign")
    private List<WebElement> productTileToPriceSign;        // $
    @FindBy(css = "span.to-price > span.price-dollar")
    private List<WebElement> productTileToPriceDollar;      // 2,446
    @FindBy(css = "span.to-price > span.price-cent")
    private List<WebElement> productTileToPriceCent;        // .99
    @FindBy(css = "div.was-reference-price")
    private List<WebElement> productTileMSRPWas;            // MSRP or Was
    @FindBy(css = "span.reference-price")
    private List<WebElement> productTileReferencePrice;     // Striked price
    @FindBy(css = "div.you-save > span.msrp-text")
    private List<WebElement> productTileYouSavePrice;           // $1,577.01 OFF
    @FindBy(css = "div.product-title.medium-title")
    private List<WebElement> productTileProdTitle;
    @FindBy(css = "div.reviews")
    private List<WebElement> productTileReviewsStars;
    @FindBy(css = "span.product-review")
    private List<WebElement> productTileReviewsCount;
    //
    public ProductTile(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean verifyProductTileSaleOnlyDisplayed() {
        for (int i=0; i <= productTileSaleOnly.size()-1; i++) {
            // Not all tile will have SALE tag
            try {
                if (i==tileNumber) {
                    return productTileSaleOnly.get(i).isDisplayed();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }
    //
    public boolean verifyProductTileWishListHearthDisplayed() {
        for (int i=0; i <= productTileWishListHeart.size()-1; i++) {
            if (i==tileNumber) {
                return productTileWishListHeart.get(i).isDisplayed();
            }
        }
        return false;
    }
    //
    public boolean verifyProductTileImgDisplayed() {
        for (int i=0; i <= productTileImg.size()-1; i++) {
            if (i==tileNumber) {
                return productTileImg.get(i).isDisplayed();
            }
        }
        return false;
    }
    //
    public void clickProductTileImg() {
        for (int i=0; i <= productTileImg.size()-1; i++) {
            if (i==tileNumber) {
                productTileImg.get(i).click();
            }
        }
    }
    //
    public boolean verifyProductTileMoreOptionsTextDisplayed() {
        for (int i=0; i <= productTileMoreOptionsText.size()-1; i++) {
            if (i==tileNumber) {
                return productTileMoreOptionsText.get(i).getTitle().equals("More Options");
            }
        }
        return false;
    }
    //
    public boolean verifyProductTilePriceTypeDisplayed() {
        for (int i=0; i <= productTilePriceType.size()-1; i++) {
            // Not all the Product Tile will be on Sale
            if (i==tileNumber) {
                return productTilePriceType.get(i).getText().contains("Sale");
            }
        }
        return false;
    }
    //
    public boolean verifyProductTileFromPriceSignDisplayed() {
        for (int i=0; i <= productTileFromPriceSign.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileFromPriceSign.get(i).getText().contains("$");
            }
        }
        return false;
    }
    //
    public String verifyProductTileFromPriceDollarDisplayed() {
        for (int i=0; i <= productTileFromPriceDollar.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileFromPriceDollar.get(i).getText();
            }
        }
        return null;
    }
    //
    public String verifyProductTileFromPriceCentDisplayed() {
        for (int i=0; i <= productTileFromPriceCent.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileFromPriceCent.get(i).getText();
            }
        }
        return null;
    }
    //
    public boolean verifyProductTileToPriceSignDisplayed() {
        for (int i=0; i <= productTileToPriceSign.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileToPriceSign.get(i).getText().contains("$");
            }
        }
        return false;
    }
    //
    public String verifyProductTileToPriceDollarDisplayed() {
        for (int i=0; i <= productTileToPriceDollar.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileToPriceDollar.get(i).getText();
            }
        }
        return null;
    }
    //
    public String verifyProductTileToPriceCentDisplayed() {
        for (int i=0; i <= productTileToPriceCent.size()-1; i++) {
            // Some price will be simple price, some will be price range
            if (i==tileNumber) {
                return productTileToPriceCent.get(i).getText();
            }
        }
        return null;
    }
    //
    public boolean verifyProductTileMSRPWasDisplayed() {
        for (int i=0; i <= productTileMSRPWas.size()-1; i++) {
            if (i==tileNumber) {
                return (productTileMSRPWas.get(i).getText().contains("MSRP") || productTileMSRPWas.get(i).getText().contains("Was"));
            }
        }
        return false;
    }
    //
    public String verifyProductTileReferencePriceDisplayed() {
        for (int i=0; i <= productTileReferencePrice.size()-1; i++) {
            if (i==tileNumber) {
                return productTileReferencePrice.get(i).getText();
            }
        }
        return null;
    }
    //
    public String verifyProductTileYouSavePriceDisplayed() {
        for (int i=0; i <= productTileYouSavePrice.size()-1; i++) {
            if (i==tileNumber) {
                return productTileYouSavePrice.get(i).getText();
            }
        }
        return null;
    }
    //
    public String verifyProductTileProdTitleDisplayed() {
        for (int i=0; i <= productTileProdTitle.size()-1; i++) {
            if (i==tileNumber) {
                return productTileProdTitle.get(i).getText();
            }
        }
        return null;
    }
    //
    public boolean verifyProductTileReviewsStarsDisplayed() {
        for (int i=0; i <= productTileReviewsStars.size()-1; i++) {
            if (i==tileNumber) {
                return productTileReviewsStars.get(i).isDisplayed();
            }
        }
        return false;
    }
    //
    public boolean verifyProductTileReviewsCountDisplayed() {
        for (int i=0; i <= productTileReviewsCount.size()-1; i++) {
            if (i==tileNumber) {
                return productTileReviewsCount.get(i).isDisplayed();
            }
        }
        return false;
    }
    //
}
