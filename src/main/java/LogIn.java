import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogIn {

    protected WebDriver driver;
    WebDriverWait wait;
    private By emailName = By.name("email");
    private By psID = By.id("ps");
    private By loginBtn = By.xpath("//*[@id=\"button-login\"]");
    private By loginerrorMsg = By.className("warning");

    public LogIn(WebDriver driver){
        this.driver = driver;
        if (!driver.getCurrentUrl().equals("https://www.periplus.com/account/Login")) {
                throw new IllegalStateException("This is not Log In Page," +
                        " current page is: " + driver.getCurrentUrl());
            }
        }

    public void LogInExpectingError(String wrongEmail, String demoPassword) {
        driver.findElement(emailName).sendKeys(wrongEmail);
        driver.findElement(psID).sendKeys(demoPassword);
        driver.findElement(loginBtn).click();
    }

    public String getErrorMsg() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(loginerrorMsg));
        return warning.getText();
    }


    public void loginValidUser(String demoEmail, String demoPassword) {
        driver.findElement(emailName).sendKeys(demoEmail);
        driver.findElement(psID).sendKeys(demoPassword);
        driver.findElement(loginBtn).click();
    }

    }
