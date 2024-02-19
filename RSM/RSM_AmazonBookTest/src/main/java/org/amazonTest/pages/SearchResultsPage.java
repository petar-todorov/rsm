package org.amazonTest.pages;

import org.amazonTest.data.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstResultTitle() {
        return driver.findElement(Locators.firstResultTitle).getText();
    }

    public void selectFirstResultPaperback() {
        driver.findElement(Locators.firstResultPaperbackOption).click();
    }

    public String getFirstResultPrice() {
        return driver.findElement(Locators.firstResultPrice).getText();
    }
}
