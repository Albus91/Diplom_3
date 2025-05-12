package my.project.chill.tests;

import io.qameta.allure.junit4.DisplayName;
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

public class AuthTest {

    private WebDriver driver;
    private ConstructorPage constructorPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoveryPasswordPage recoveryPage;
    private Header header;
    private AccountPage accountPage;

    String email = ConfigReader.get("user.email");
    String password = ConfigReader.get("user.password");
    String name = ConfigReader.get("user.name");

    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        UserClient.UserCreate newUser = new UserClient.UserCreate(email, password, name);
        userClient.createUser(newUser);
        accessToken = userClient.loginUser(new UserClient.UserLogin(email, password));
        driver = new Browser().getWebDriver(System.getProperty("browser", "chrome"));
        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        recoveryPage = new RecoveryPasswordPage(driver);
        header = new Header(driver);
        accountPage = new AccountPage(driver);
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
    @DisplayName("Логин на странице конструктора")
    public void loginViaMainLoginButton() {
        driver.get(CONSTRUCTOR_URL);
        constructorPage.clickLogin();
        loginPage.login(email, password);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        constructorPage.isAtConstructorPage();
    }

    @Test
    @DisplayName("Логин на странице личного кабинета")
    public void loginViaAccountPageButton() {
        driver.get(CONSTRUCTOR_URL);
        header.clickProfile();
        loginPage.login(email, password);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        constructorPage.isAtConstructorPage();
    }

    @Test
    @DisplayName("Логин на странице регистрации")
    public void loginViaRegistrationFormLink() {
        driver.get(REGISTER_URL);
        registrationPage.clickLoginLink();
        loginPage.login(email, password);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        constructorPage.isAtConstructorPage();
    }

    @Test
    @DisplayName("Логин на странице забытого пароля")
    public void loginViaRecoveryFormLink() {
        driver.get(FORGOT_PASS_URL);
        recoveryPage.clickLoginLink();
        loginPage.login(email, password);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        constructorPage.isAtConstructorPage();
    }

    @Test
    @DisplayName("Логаут")
    public void testLogout() {
        driver.get(LOGIN_PAGE_URL);
        loginPage.login(email, password);
        header.clickProfile();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        accountPage.clickLogout();
        loginPage.isAtLoginPage();
    }
}