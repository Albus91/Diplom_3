package my.project.chill.tests;

import io.qameta.allure.junit4.DisplayName;
import my.project.chill.utils.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import uz.myproject.chill.pageobjects.ConstructorPage;

import static my.project.chill.constants.URLs.*;

public class IngridientsNavigationTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    private static final String BROWSER  = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        driver = new Browser().getWebDriver(BROWSER);
        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        driver.get(CONSTRUCTOR_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @DisplayName("Проверка видимости секции булок")
    public void bunsTabShouldShowBunsSection() {
        constructorPage.clickSauces();
        constructorPage.clickBuns();
        constructorPage.isBunsSectionVisible();
    }

    @Test
    @DisplayName("Проверка видимости секции соусов")
    public void saucesTabShouldShowSaucesSection() {
        constructorPage.clickSauces();
        constructorPage.isSaucesSectionVisible();
    }

    @Test
    @DisplayName("Проверка видимости секции начинок")
    public void fillingsTabShouldShowFillingsSection() {
        constructorPage.clickFilling();
        constructorPage.isFillingsSectionVisible();
    }
}
