package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.ResultsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeMeSteps {
    private WebDriver driver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @Given("I am conducting a TradeMe search")
    public void i_am_conducting_a_trade_me_search() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        if (("true").equals(System.getenv("HEADLESS_CHROME"))) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        driver.get("https://www.tmsandbox.co.nz/");
        homePage = new HomePage(driver);
    }
    @When("I search for {string}")
    public void i_search_for(String string) {
        resultsPage = homePage.searchForGold("gold");
    }

    @Then("I see {int} results")
    public void i_see_results(Integer numResults) {
        assertEquals(numResults, resultsPage.getTotalCount());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
