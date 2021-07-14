import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest {

    private WebDriver driver;

    @BeforeAll
    private static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void setUpBrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @AfterEach
    private void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkTitle() {
        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void testCheese() throws Exception {
        WebElement queryBox = driver.findElement(By.name("q"));
        queryBox.sendKeys("cheese");
        queryBox.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }
}
