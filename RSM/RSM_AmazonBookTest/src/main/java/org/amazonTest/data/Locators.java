package org.amazonTest.data;

import org.openqa.selenium.By;

public class Locators {
    // Amazon Home Page
    public static final By searchDropdownBox = By.id("searchDropdownBox");
    public static final By searchInput = By.id("twotabsearchtextbox");
    public static final By searchButton = By.id("nav-search-submit-button");

    // Search Results Page
    public static final By firstResultTitle = By.cssSelector(".s-search-results .s-result-item .a-link-normal.a-text-normal");
    public static final By firstResultPaperbackOption = By.xpath("//a[contains(@class, 'a-size-base a-link-normal') and contains(text(), 'Paperback')]");
    public static final By firstResultPrice = By.cssSelector(".s-search-results .s-result-item .a-price");

    // Product Details Page
    public static final By productTitle = By.id("productTitle");
    public static final By addToBasketButton = By.id("add-to-cart-button");
    public static final By giftCheckbox = By.name("isGift");

    // Basket Page
    public static final By basketLink = By.id("nav-cart");
    public static final By basketItemTitle = By.cssSelector(".sc-list-item-content .a-link-normal.sc-product-link");
    public static final By basketItemPrice = By.cssSelector(".sc-list-item-content .a-size-medium.a-color-base.sc-price");
    public static final By basketItemCount = By.cssSelector(".sc-subtotal-label-buybox");
    public static final By availabilityStatus = By.id("availability");
    public static final By customerReviews = By.id("customerReviews");
}
