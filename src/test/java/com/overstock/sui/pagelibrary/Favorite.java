package com.overstock.sui.pagelibrary;

//import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Search for sofas and couches
 * On the Result page, heart icon should be displayed
 * Click on the heart button
 * Verify Favorite modal window should open
 * Verify Favorite is checked
 * Verify Favorite heart for the product is Red (data-is-favorited="true")
 * Again on the same product, click on the Red heart icon
 * Verify Favorite modal window opens
 * Verify Favorite is unchecked
 * Verify Favorite heart for the product is not Red (data-is-favorited="false")
 * Save For Later
 * Create New List
 *
 *
*/

public class Favorite {
    WebDriver driver;
    //
    @FindBy(css = "div.wishlist-heart-container.list-creation.list-modal")
    List<WebElement> favHeart;          // Heart is Red or Gray
    @FindBy(css = "div.list-mobile-active")
    List<WebElement> favFavoritesActive;      // Favorites / Save For Later / Create New List
    @FindBy(css = "img.red")
    List<WebElement> favRed;            // Favorite selected
    @FindBy(css = "img.gray")
    List<WebElement> favGray;           // Favorite not selected
    @FindBy(css = "span.selected")
    List<WebElement> favSelected;       // Favorites | Save For Later | Create New List
    @FindBy(css = "#search-input")
    WebElement headerSearchInputBox;
    //
    public Favorite (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //
    public int countFavorites() {
        return favHeart.size();
    }
    //
    public boolean isFavorite(int pos) {
        for (int i=0; i<=favHeart.size()-1; i++) {
            if (i==pos) {
                if (favRed.get(i).getAttribute("red").equals("red")) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    //
    public boolean setFavorite(int pos, String favUnFavorite) {
        boolean bPass=false;
        for (int i = 0; i <= favHeart.size() - 1; i++) {
            if (i == pos) {
                if ((favUnFavorite.equals("MakeFavorite")) && (favGray.get(i).getAttribute("data-is-favorited").equals("false"))) {
                    favGray.get(i).click();         // make Favorite
                    headerSearchInputBox.click();   // just to move away from the Favorite open dialog box
                    bPass = true;
                }
                if ((favUnFavorite.equals("RemoveFavorite")) && (favRed.get(i).getAttribute("data-is-favorited").equals("true"))) {
                    favRed.get(i).click();          // remove Favorite
                    headerSearchInputBox.click();   // just to move away from the Favorite open dialog box
                    bPass=true;
                }
                break;
            }
        }
        return bPass;
    }
    //
    public void saveForLater(int pos) {
        for (int i=0; i<=favHeart.size()-1; i++) {
            if (i==pos) {
                favHeart.get(i).click();
                for (int j=0; j<=favSelected.size()-1; j++) {
                    if (favSelected.get(j).getText().equals("Save For Later")) {
                        favSelected.get(j).click();
                    }
                }
            }
        }
    }
    //
    public boolean verifyFavoriteSelected(int pos, int favoritesOrSaveForLater) {
        for (int i=0; i<=favHeart.size()-1; i++) {
            if (i==pos) {
                for (int j=0; j<=favSelected.size()-1; j++) {
                    if (j==favoritesOrSaveForLater) {
                        if (favSelected.get(j).getAttribute("viewBox").equals("0 0 24 24")) {               // favorite not checked
                            return false;
                        } else if ((favSelected).get(j).getAttribute("viewBox").equals("0 0 16 16")) {      // favorite checked
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //
}
