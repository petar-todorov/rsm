package org.amazonTest.pages;

import org.amazonTest.data.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonHomePage {
    private WebDriver driver;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForBook(String bookName) {
        driver.findElement(Locators.searchDropdownBox).sendKeys("Books");
        driver.findElement(Locators.searchInput).sendKeys(bookName);
        driver.findElement(Locators.searchButton).click();
    }
}
