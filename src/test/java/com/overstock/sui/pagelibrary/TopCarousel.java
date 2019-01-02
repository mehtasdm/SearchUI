package com.overstock.sui.pagelibrary;

import com.overstock.sui.testbase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by smehta on 12/23/2017.
 * https://www.overstock.com/Home-Garden/Area-Rugs/Outdoor,/rug-type,/244/cat.html?TID=ARSP:00:LeftNav-4:Indoor/OutdoorRugs
 * Verify the right arrow on Carousel is clickable
 * Verify the left arrow on Carousel is clickable
 * Verify the taxonomy tile in Carousel is clickable
 * Verify on click of taxonomy tile takes to subcat page
 * Verify Top Carousel is persistent (i.e. it appears on every page, on SPA actions viz. sort, filter)
 * Verify Top Carousel should not appear on pagination
 */

public class TopCarousel extends TestBase {
    //
    WebDriver driver;
    //
    @FindBy(css = "div.arrow.right")                                // assuming on the testUrl there are two carousels (the Top Carousel and Related Reviews Carousel)
    List<WebElement> lweArrowRight;
    @FindBy(css = "div.arrow.left")
    List<WebElement> lweArrowLeft;
    @FindBy(css = "div.arrow.left.disabled")
    List<WebElement> lweArrowLeftDisabled;
    @FindBy(css = "a>img.img-responsive")
    List<WebElement> leRefinementImage;
    @FindBy(xpath = ".//*[@class='slider']/div/div/div[1]/a/p")      // Categories
    WebElement refinementLink;
    //
    public TopCarousel(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public boolean verifyArrowLeftDisabled() {
        for (WebElement weArrowLeft : lweArrowLeftDisabled) {
            return weArrowLeft.isEnabled();                 // for Top Carousel return on the first WebElement
        }
        return false;
    }
    //
    public boolean verifyArrowLeftEnabled() {
        for (WebElement weArrowLeft : lweArrowLeft) {
            return weArrowLeft.isEnabled();                 // for Top Carousel return on the first WebElement
        }
        return false;
    }
    //
    public boolean verifyArrowRightEnabled() {
        for (WebElement weArrowRight : lweArrowRight) {
            return weArrowRight.isEnabled();                // for Top Carousel return on the first WebElement
        }
        return false;
    }
    //
    public void clickArrowRight() {
        for (WebElement weArrowRight : lweArrowRight) {
            weArrowRight.click();                           // for Top Carousel return on the first WebElement
            break;
        }
    }
    //
    public void clickArrowLeft() {
        for (WebElement weArrowLeft : lweArrowLeft) {
            weArrowLeft.click();
            break;
        }
    }
    //
    public void clickRefinementImage() {
        for (WebElement weRefImg : leRefinementImage) {
            weRefImg.click();
            break;
        }
    }
    //
    public void clickRefinementLink() {
        refinementLink.click();
    }
    //
    public boolean isCarouselPresent() {
        if (leRefinementImage.size()==0) {
            return false;
        } else return true;
    }
    //
}
