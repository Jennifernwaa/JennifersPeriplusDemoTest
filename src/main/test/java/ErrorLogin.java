import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ErrorLogIn {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String Email = "wrongemail@gmail.com";
    private final String demoPassword = "Testing.123";

    @BeforeClass
    public void testSetupBrowser() {
        String baseUrl = "https://www.periplus.com/";
        SetupBrowser objSetupBrowser = new SetupBrowser(baseUrl);
        driver = objSetupBrowser.getDriver();
        objSetupBrowser.clickSignInBtn();
        System.out.println("Opened Periplus website.");
    }

    @Test(priority = 1)
    public void testLogin() {
        LogIn logInPage = new LogIn(driver);
        logInPage.LogInExpectingError(Email, demoPassword);

        String errorMessage = logInPage.getErrorMsg();
        System.out.println("Login unsuccessful: "+errorMessage);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Periplus Page is closed.");
        }
    }
}

