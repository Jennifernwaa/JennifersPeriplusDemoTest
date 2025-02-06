import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SetupBrowser {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public SetupBrowser(String baseUrl) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get(baseUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void clickSignInBtn(){
        driver.findElement(By.id("nav-signin-text")).click();
    }
}
