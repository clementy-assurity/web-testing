import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.ResultsPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeMeTest {

    private WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @BeforeAll
    private static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void setUpBrowser() {
        setupChromeDriver();
        homePage = new HomePage(driver);
    }

    private void setupChromeDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.tmsandbox.co.nz/");

    }

    @AfterEach
    private void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkTitle() {
        assertEquals("TradeMe", driver.getTitle());
    }

    @Test
    public void testCheese() throws Exception {
        resultsPage = homePage.searchForGold();
        Thread.sleep(5000);
        WebElement numResults = resultsPage.getTotalCount();
        WebElement topPrice = driver.findElement(By.cssSelector("#SuperGridGallery_BucketList_ClassifiedPrice_listingClassifiedPriceAmountPoa"));
        WebElement listViewButton = driver.findElement(By.cssSelector("#ListingViewBar_listViewTab_icon_a > img"));
        System.out.println("Number of search results: " + numResults.getText());
        System.out.println("Top result price: " + topPrice.getText());
        Thread.sleep(5000);
        listViewButton.click();
        Thread.sleep(5000);
        String[] titles = {"This is First Home Gold", "Cardrona Gold", "Cardrona Gold", "Cardrona Gold", "Cardrona Gold", "Cardrona Gold", "Cardrona Gold", "Freehold gold in Whangarei", "Albany Retail Gold", "Porana Road Gold"};
        for (int titleNumber = 1; titleNumber <= 10; titleNumber++) {
            WebElement currentTitleNumber = driver.findElement(By.cssSelector("#mainContent > div.supergrid-overlord > div:nth-child(" + titleNumber + ") > a > div > div.location-wrapper > div.info > div.title"));
            System.out.println("Listing name " + titleNumber + ": " + currentTitleNumber.getText());
            assertEquals(titles[titleNumber - 1], currentTitleNumber.getText());
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ListingsTitle_SortBySpan")));

        Select select = new Select(driver.findElement(By.cssSelector("#listingTitleBarSelect")));
        select.selectByVisibleText("Lowest price");

        Thread.sleep(5000);

        List<WebElement> allPrices = driver.findElements(By.cssSelector("#SuperListView_BucketList_BidInfo_listingBidPrice"));
        for (int priceIndex = 0; priceIndex < 3; priceIndex++) {
            System.out.println(allPrices.get(priceIndex).getText());
        }
    }

}
