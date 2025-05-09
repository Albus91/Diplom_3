package my.project.chill.tests;

import my.project.chill.api.UserClient;
import my.project.chill.utils.Browser;
import my.project.chill.utils.ConfigReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import uz.myproject.chill.pageobjects.*;

import java.util.concurrent.TimeUnit;

import static my.project.chill.constants.URLs.*;

public class RegistrationTests {

    private static final String BROWSER = System.getProperty("browser", "chrome");
    String email = ConfigReader.get("user.email");
    String password = ConfigReader.get("user.password");
    String name = ConfigReader.get("user.name");
    String shortPassword = ConfigReader.get("user.shortpassword");

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        Browser browserFactory = new Browser();
        driver = browserFactory.getWebDriver(BROWSER);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.get(REGISTER_URL);
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessfulRegistration() {
        registrationPage.register(name, email, password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage.isAtLoginPage();
        accessToken = userClient.loginUser(
                new UserClient.UserLogin(email, password)
        );
    }

    @Test
    public void testUnSuccessfulRegistration() {
        registrationPage.register(name, email, shortPassword);
        registrationPage.getPasswordErrorMessage();
    }
}
