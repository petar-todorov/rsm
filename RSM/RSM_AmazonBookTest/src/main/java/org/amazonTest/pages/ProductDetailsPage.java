package org.amazonTest.pages;

import org.amazonTest.data.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductDetailsPage {
    private WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        return driver.findElement(Locators.productTitle).getText();
    }

    public String getPrice() {
        // This method assumes the price element is visible and fetches its text.
        // Adjust the locator if the structure of the page changes.
        return driver.findElement(Locators.firstResultPrice).getText();
    }

    public boolean isPaperback() {
        // This method checks if the page contains information indicating it's a paperback edition.
        // Implement this check based on the specific structure and content of the Amazon product page.
        WebElement paperbackEdition = driver.findElement(By.xpath("//a[contains(text(), 'Paperback')]"));
        return paperbackEdition != null;
    }

    public void addToBasket() {
        driver.findElement(Locators.addToBasketButton).click();
    }

    public void markAsGift() {
        WebElement giftCheckbox = driver.findElement(Locators.giftCheckbox);
        if (!giftCheckbox.isSelected()) {
            giftCheckbox.click();
        }
    }

    public boolean hasCustomerReviews() {
        List<WebElement> reviews = driver.findElements(Locators.customerReviews);
        return !reviews.isEmpty();
    }

    public boolean isAvailable() {
        WebElement availability = driver.findElement(Locators.availabilityStatus);
        return availability.getText().contains("In stock");
    }
}
