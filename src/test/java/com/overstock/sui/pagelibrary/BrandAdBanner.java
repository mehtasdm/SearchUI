package com.overstock.sui.pagelibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Verify Single or Double Brand Ad Banner below Pagination and above Related Reviews
 * Verify brand-ad-image
 * Verify brand-ad-logo
 * Verify brand-ad-savings      // Up to 60% off | Up to 60% off + Extra 35% off | Save on
 * Verify Title                 // Select Window Treatments | Everything for Your Home
 * Verify click on link
 */

public class BrandAdBanner {
    //
    WebDriver driver;
    //
    @FindBy(css = "div.brand-ad")
    List<WebElement> brandAdBanners;
    @FindBy(css = "div.brand-ad-logo")
    List<WebElement> brandAdLogo;
//    @FindBy(css = "div.brand-ad-savings")
//    List<WebElement> brandAdSavings;
    @FindBy(css = "div.brand-ad-image")
    List<WebElement> brandAdImage;
    @FindBy(css = "span.percent-off")
    List<WebElement> brandAdTitle;
//    @FindBy(css = "")
//    List<WebElement> brandAdSavings;
    //
    public BrandAdBanner(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public int countBrandAdBanner() {
        return brandAdBanners.size();
    }
    //
    public void clickBrandAdBanner(int pos) {
        for (int i=0; i <= brandAdBanners.size()-1; i++) {
            if (i==pos) {
                brandAdBanners.get(i).click();
            }
        }
    }
    //
    public int countBrandAdBannerLogo() {
        return brandAdLogo.size();
    }
    //
    public int countBrandAdBannerImage() {
        return brandAdImage.size();
    }
    //
    public String titleBrandAdBanner(int pos) {
        for (int i=0; i<= brandAdTitle.size()-1; i++) {
            if (i==pos) {
//                System.out.println(brandAdTitle.get(i).getText());
                return brandAdTitle.get(i).getText();
            }
        }
        return null;
    }
    //
}
