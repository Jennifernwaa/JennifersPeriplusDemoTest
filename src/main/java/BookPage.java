import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BookPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private By addToCartBtn = By.className("btn-add-to-cart");
    private By cartIcon = By.id("show-your-cart");
    private By preloader = By.className("preloader");

    public BookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);

    }


    public void addToCart() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        WebElement addBook = driver.findElement(addToCartBtn);
        addBook.click();
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
    }

    public void openCart() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
        WebElement cart = driver.findElement(cartIcon);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("show-your-cart")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Notification-Modal")));
        actions.moveToElement(cart).perform();
        cart.click();
    }

}
