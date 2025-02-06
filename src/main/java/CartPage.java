import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private By preloader = By.className("preloader");
    private By quantityWanted = By.className("input-number");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);

    }
    public void increaseItemQty(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        WebElement qty = driver.findElement(quantityWanted);
        qty.sendKeys("4");
    }

    public void removeBook(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        WebElement removeButton = driver.findElement(By.className("btn-cart-remove"));
        removeButton.click();
    }

    public String getCartDetails(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));

        By productNameLocator = By.xpath("//*[@id='basket']/div/div/div/div/div/div/div[1]/div[2]/div[1]/p/a");
        By totalPriceLocator = By.cssSelector("#sub_total");

        String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator)).getText();
        String totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceLocator)).getText();

        return "Product: " + productName + "\nTotal Price: " + totalPrice;
    }

    public void Logout(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        WebElement logOut = driver.findElement(By.id("nav-signin-text"));
        actions.moveToElement(logOut).perform();
        driver.get("https://www.periplus.com/_index_/Logout/");
    }
}
