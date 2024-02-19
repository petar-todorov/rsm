package org.amazonTest.pages;

import org.amazonTest.data.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketPage {
    private WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openBasket() {
        driver.findElement(Locators.basketLink).click();
    }

    public String getItemTitle() {
        return driver.findElement(Locators.basketItemTitle).getText();
    }

    public String getItemPrice() {
        return driver.findElement(Locators.basketItemPrice).getText();
    }

    public boolean isItemMarkedAsGift() {
        // This method checks if the item in the basket is marked as a gift.
        // The implementation depends on how Amazon indicates this on the basket page.
        // Assuming there's an element that signifies gift status, adjust the locator and logic as needed.
        WebElement giftIndicator = driver.findElement(Locators.giftCheckbox);
        return giftIndicator != null;
    }

    public int getItemCount() {
        // This method counts the number of items in the basket.
        // Assuming each item has a specific class or identifier, adjust the locator and count logic as needed.
        List<WebElement> items = driver.findElements(By.cssSelector(".sc-list-item"));
        return items.size();
    }

    public boolean verifyItemEdition(String expectedEdition) {
        WebElement itemEdition = driver.findElement(Locators.basketItemTitle);
        return itemEdition.getText().contains(expectedEdition);
    }

    public boolean verifyItemAsGift() {
        // This assumes there is a specific way items marked as gifts are displayed in the basket
        WebElement giftIndicator = driver.findElement(Locators.giftCheckbox);
        return giftIndicator != null && giftIndicator.isDisplayed();
    }

    public boolean verifySingleItemInBasket() {
        return getItemCount() == 1;
    }
}
