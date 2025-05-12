package my.project.chill.tests;

import io.qameta.allure.junit4.DisplayName;
import my.project.chill.api.UserClient;
import my.project.chill.utils.Browser;
import my.project.chill.utils.ConfigReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import uz.myproject.chill.pageobjects.AccountPage;
import uz.myproject.chill.pageobjects.Header;
import uz.myproject.chill.pageobjects.LoginPage;
import uz.myproject.chill.pageobjects.ConstructorPage;

import java.util.concurrent.TimeUnit;

import static my.project.chill.constants.URLs.*;

public class NavigationTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;
    private AccountPage accountPage;
    private Header header;

    private static final String BROWSER  = System.getProperty("browser", "chrome");
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
        driver = new Browser().getWebDriver(BROWSER);
        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        header = new Header(driver);
        driver.get(LOGIN_PAGE_URL);
        loginPage.login(email, password);
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
    @DisplayName("Переход к странице личного кабинета со страницы конструктора")
    public void navigateToAccountPageFromConstructorPage() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        header.clickProfile();
        accountPage.isAtAccountPage();
    }

    @Test
    @DisplayName("Переход к странице конструктора из личного кабинета по клику на конструктор")
    public void navigateToConstructorPageFromAccountPageByConstructorLink() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        header.clickProfile();
        header.clickConstructor();
        constructorPage.isAtConstructorPage();
    }

    @Test
    @DisplayName("Переход к странице конструктора из личного кабинета по клику на лого")
    public void navigateToConstructorPageFromAccountPageByLogo() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        header.clickProfile();
        header.clickLogo();
        constructorPage.isAtConstructorPage();
    }
}
