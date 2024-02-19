package searchTests;

import org.amazonTest.pages.AmazonHomePage;
import org.amazonTest.pages.BasketPage;
import org.amazonTest.pages.ProductDetailsPage;
import org.amazonTest.pages.SearchResultsPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonBookSearchAndVerifyTest {

    private WebDriver driver = new ChromeDriver();

    @Test
    public void searchBookAndVerifyDetails() {
        driver.get("https://www.amazon.co.uk");
        assert driver.getTitle().contains("Amazon.co.uk");

        AmazonHomePage homePage = new AmazonHomePage(driver);
        homePage.searchForBook("Harry Potter and the Cursed Child Parts One & Two");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        assert searchResultsPage.getFirstResultTitle().contains("Harry Potter and the Cursed Child");

        String initialPrice = searchResultsPage.getFirstResultPrice();
        searchResultsPage.selectFirstResultPaperback();

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        assert productDetailsPage.getProductTitle().contains("Harry Potter and the Cursed Child");
        assert productDetailsPage.isPaperback();
        assert productDetailsPage.getPrice().equals(initialPrice);

        productDetailsPage.addToBasket();
        productDetailsPage.markAsGift();

        BasketPage basketPage = new BasketPage(driver);
        basketPage.openBasket();

        assert basketPage.getItemTitle().contains("Harry Potter and the Cursed Child");
        assert basketPage.isItemMarkedAsGift();
        assert basketPage.getItemPrice().equals(initialPrice);
        assert basketPage.getItemCount() == 1;

        driver.quit();
    }
}
