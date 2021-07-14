package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultsPage searchForGold() {
        WebElement queryBox = driver.findElement(By.cssSelector("#searchString"));
        queryBox.sendKeys("gold");
        // queryBox.sendKeys(Keys.RETURN);
        // queryBox.submit();
        WebElement submitButton = driver.findElement(By.cssSelector("#generalSearch > div.field.field-right > button"));
        submitButton.click();
        return new ResultsPage(driver);
    }
}
