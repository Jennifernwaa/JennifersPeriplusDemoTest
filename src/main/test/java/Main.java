import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main {
    private WebDriver driver;
    private WebDriverWait wait;



    private final String baseUrl = "https://www.periplus.com/";
    private final String demoEmail = "jennifernwachinaemere@gmail.com";
    private final String demoPassword = "Testing.123";

    @BeforeClass
    public void testSetupBrowser() {
        SetupBrowser objSetupBrowser = new SetupBrowser(baseUrl);
        driver = objSetupBrowser.getDriver();
        objSetupBrowser.clickSignInBtn();
        System.out.println("Opened Periplus website.");
    }

    @Test(priority = 1)
    public void testLogin() {
        LogIn logInPage = new LogIn(driver);
        logInPage.loginValidUser(demoEmail, demoPassword);
        System.out.println("Login successful");
    }

    @Test(priority = 2, dependsOnMethods = {"testLogin"})
    public void testSearchBook() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.searchForBook("The Bell Jar");
        System.out.println("Searched for 'The Bell Jar'.");
        objHomePage.selectBook();
    }

    @Test(priority = 3, dependsOnMethods = {"testSearchBook"})
    public void testAddToCart() {
        BookPage objBookPage = new BookPage(driver);
        objBookPage.addToCart();
        System.out.println("Book added to cart.");
    }

    @Test(priority = 4, dependsOnMethods = {"testAddToCart"})
    public void testOpenCartAndVerify() {
        BookPage objBookPage = new BookPage(driver);
        objBookPage.openCart();
    }

    @Test(priority = 5, dependsOnMethods = {"testOpenCartAndVerify"})
    public void testIncreaseQuantity() {
        //increase quantity
        CartPage objCartPage = new CartPage(driver);
        objCartPage.increaseItemQty();
        System.out.println("Increased item quantity.");
        //Get cart details
        String cartDetails = objCartPage.getCartDetails();
        System.out.println("Cart Details:\n" + cartDetails);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Periplus Page is closed.");
        }
    }
}
