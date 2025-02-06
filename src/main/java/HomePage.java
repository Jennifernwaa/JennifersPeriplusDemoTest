import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    private By BookSelected = By.xpath("//div[contains(@class, 'row-category-grid')]/div[1]");
    private By preloader = By.className("preloader");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        if (!driver.getCurrentUrl().equals("https://www.periplus.com/account/Your-Account")) {
            throw new IllegalStateException("This is not Home Page of logged in user," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public void searchForBook(String BookName){
        WebElement searchInput = driver.findElement(By.id("filter_name"));
        searchInput.sendKeys(BookName);
        searchInput.submit();
    }

    public void selectBook() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        WebElement book = driver.findElement(BookSelected);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        driver.get("https://www.periplus.com/p/9789357025997/the-bell-jar?filter_name=The%20Bell%20Jar");
    }
}

