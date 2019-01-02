package com.overstock.sui.pagelibrary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.awt.windows.WEmbeddedFrame;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Verify Delivery 2-Day Shipping is displayed
 * Top 5 Refinements should be expanded
 * Rest Refinements should be collapsed
 * Refinement filters get applied
 * Refinement filter applied on collapsed filters should get expanded
 * Refinement filter removed on collapsed/expanded filters should stay expanded
 * Refinements breadcrumb
 * Clear All link visible when two or more refinements are applied
**/
public class LeftNavigation {
    //
    WebDriver driver;
    //
    @FindBy(css = "h1.search-nav-h1")
    WebElement searchNavH1;                                     // 7x9 - 10x14 Rugs || 7x9 - 10x14 Rugs with Free 2-Day Shipping
    @FindBy(css = "h3.section-heading")
    WebElement deliveryHeading;                                 // Delivery
    @FindBy(css = "span.refinement-text.free-shipping")
    WebElement lblFreeShipping;                                 // 2-Day Shipping
    @FindBy(css = "li#free-shipping-li.refinement-link.multiselect")
    WebElement chkboxFreeShipping;                              // Checkbox for 2-Day Shipping
    @FindBy(css = "span.zip-code")
    WebElement lblZipCode;                                      // Zip Code:
    @FindBy(css = "#zip-code-input")
    WebElement zipCode;                                         // Input Zip Code
    @FindBy(css = "button#zip-code-button")
    WebElement zipCodeButton;                                   // Go
//    @FindBy(css = "span.refinement-group-wrapper")
//    List<WebElement> leftNavRefinements;                        // Sizes | Mattress Types | Thickness | Brands | Product Features || Mattress Supports | Prices | Mattress Top | Avg. Customer Reviews | Sales & Promotions
    @FindBy(css = "div.refinement-group")
    List<WebElement> leftNavRefinements;                        // Mattress Supports | Prices | Mattress Top | Avg. Customer Reviews | Sales & Promotions
//    @FindBy(css = "input.instant-search-input")
//    @FindBy(css = "#colors .refinement-group .instant-search-wrapper")
    @FindBy(css = ".instant-search-input")
    List<WebElement> leftNavSearchWithin;                             // Colors Refinement
//    List<WebElement> leftNavSearchWithin;                       // Colors Refinement
//    @FindBy(css = "a.refinement-link.multiselect")
//    @FindBy(css = "#colors .refinement-group .refinements .refinement-item .refinement-link.multiselect")
    @FindBy(css = "span.refinement-text")
    List<WebElement> innerRefinements;                          // Colors=Red, Blue.... || Rug Sizes=8'x10', 9'x12'... || Styles=Contemporary, Modern...
    @FindBy(css = "li.restriction")
    List<WebElement> restrictionsOnTop;                         // Remove Restrictions/Refinements/Filters
    @FindBy(css = "ul#priceList.refinements>li.refinement-item")
    List<WebElement> liPriceRefinements;                        // Price Refinement List Under $250 | $250 - $400 | $400 - $600 | $600 - $1,500 | $1,500+
    @FindBy(css = "input[name='rangeminprice']")
    WebElement priceMin;                                        // $min
    @FindBy(css = "input[name='rangemaxprice']")
    WebElement priceMax;                                        // $max
    @FindBy(css = "button.price-range-btn.btn")
    WebElement priceGoButton;                                   // Go
    @FindBy(css = "li.clear-all")
    WebElement clearAllRefinements;                             // Clear All
    // "https://www.overstock.com/Home-Garden/7x9-10x14-Rugs/608/subcat.html";
    // https://www.overstock.com/Home-Garden/Mattresses/Queen,/size,/2019/subcat.html
    //
    public LeftNavigation(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public String getDeliveryHeading() {
        return deliveryHeading.getText();
    }
    //
    public String getFreeShippingText() {
        return lblFreeShipping.getText();
    }
    //
    public String getZipCode() {
        return lblZipCode.getText();
    }
    //
    public void setZipCode(String strZipCode) throws NoSuchElementException {
        zipCode.clear();
        zipCode.sendKeys(strZipCode);
        zipCodeButton.click();
    }
    //
    public boolean freeShippingIsSelectedH1() throws NoSuchElementException {
        System.out.println(searchNavH1.getText());
        if (searchNavH1.getText().contains("with Free 2-Day Shipping")) {
            return true;
        } else return false;
    }
    //
    public boolean freeShippingIsSelectedCheckbox() throws NoSuchElementException {
        if (chkboxFreeShipping.getAttribute("class").contains("selected")) {
            return true;
        } else return false;
    }
    //
    public boolean freeShippingIsSelectedRefinement() throws NoSuchElementException {
        boolean bPass=false;
        for (WebElement weR : restrictionsOnTop) {
            if (weR.getAttribute("title").contains("shipping")) {          // [Shipping: 2-Day Delivery] refinement
                bPass = true;
                break;
            }
        }
        return bPass;
    }
    //
    public void clickFreeShipping() {
        chkboxFreeShipping.click();
    }
    //
    public Integer numOfRefinementsExpanded() throws IndexOutOfBoundsException {
        int num=0;
        for (int i=0; i<leftNavRefinements.size(); i++ ) {
            if (!leftNavRefinements.get(i).getAttribute("class").contains("closed")) {
                num++;
            }
        }
        return num;
    }
    //
    public Integer numOfRefinementsOnTopAppllied() {
        return restrictionsOnTop.size();
    }
    //
    public boolean collapseRefinement(int num) throws IndexOutOfBoundsException {
        boolean bPass=false;
        for (int i=0; i<=num; i++) {
            if (i==num) {
                if (leftNavRefinements.get(i).getAttribute("class").contains("closed")) {
                    bPass=false;                                    //
                } else {
                    leftNavRefinements.get(i).click();              // Collapse when it is Expanded
                    bPass=true;                                     //
                }
            }
        }
        return bPass;
    }
    //
    public boolean expandRefinement(int num) throws IndexOutOfBoundsException {
        boolean bPass=false;
        for (int i=0; i<=num; i++) {
            if (i==num) {
                if (leftNavRefinements.get(i).getAttribute("class").contains("closed")) {
                    bPass=true;
                    leftNavRefinements.get(i).click();              // Expand when it is collapsed
                } else bPass=false;                                 //
            }
        }
        return bPass;
    }
    //
    public boolean areLeftNavRefinementsExpanded(boolean openAllRefinements) throws NoSuchElementException {
        boolean bPass=true;
        for (int i=0; i <= leftNavRefinements.size()-1; i++) {
            if (openAllRefinements || i <= 6) {                           // i <=6, because i=0, is 2-Day Shipping Delivery || Refinements should be Expanded
                try {
                    if (leftNavRefinements.get(i).getAttribute("class").contains("closed")) {
//                        System.out.println(leftNavRefinements.get(i));
                        bPass=false;
                    }
                } catch (Exception e) {
                    System.err.println("*** " + leftNavRefinements.get(i) + " ***");
                }
            }
        }
        return bPass;
    }
    //
    public boolean areLeftNavRefinementsCollapsed(boolean openAllRefinements) throws NoSuchElementException {
        boolean bPass=true;
        for (int i=0; i <= leftNavRefinements.size()-1; i++) {
            if (openAllRefinements || i > 6) {                     // Refinements should be Collapsed
                try {
                    if (!leftNavRefinements.get(i).getAttribute("class").contains("closed")) {
//                        System.out.println(leftNavRefinements.get(i));
                        bPass=false;
                    }
                } catch (Exception e) {
                    System.err.println("*** " + leftNavRefinements.get(i) + " ***");
                }
            }
        }
        return bPass;
    }
    //
    public void setMinPrice(int price) {
        priceMin.sendKeys(String.valueOf(price));
    }
    //
    public void setMaxPrice(int price) {
        priceMax.sendKeys(String.valueOf(price));
    }
    //
    public void clickPriceGoButton() {
        priceGoButton.click();
    }
    //
    public void clickClearAll() {
        clearAllRefinements.click();
    }
    //
    public void setSearchWithin(String param, String paramValue) {
        for (WebElement weSW : leftNavSearchWithin) {
            if (weSW.getAttribute("placeholder").contains(param)) {
                weSW.sendKeys(paramValue);
                break;
            }
        }
    }
    //
    public void clickInnerRefinement(String innerRefine) {
        for (WebElement weIR : innerRefinements) {
            if (weIR.getText().equals(innerRefine)) {
//                JavascriptExecutor jse = (JavascriptExecutor)driver;
//                jse.executeScript("arguments[0].click;", weIR);
                weIR.click();
                break;
            }
        }
    }
    //
    public void applyPriceRefinement(int num) {
        for (int i=0; i<=liPriceRefinements.size()-1; i++) {
            if (i==num) {
                liPriceRefinements.get(i).click();
            }
        }
    }
}
