package uz.myproject.chill.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConstructorPage {

    public ConstructorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement toLoginButton;

    @FindBy(xpath = "//div[contains(@class,'tab_tab') and .//span[text()='Булки']]")
    private WebElement buns;

    @FindBy(xpath = "//div[contains(@class,'tab_tab') and .//span[text()='Соусы']]")
    private WebElement sauces;

    @FindBy(xpath = "//div[contains(@class,'tab_tab') and .//span[text()='Начинки']]")
    private WebElement fillings;

    @FindBy(xpath = "//h2[text()='Булки']")
    private WebElement bunsSectionHeader;

    @FindBy(xpath = "//h2[text()='Соусы']")
    private WebElement saucesSectionHeader;

    @FindBy(xpath = "//h2[text()='Начинки']")
    private WebElement fillingsSectionHeader;

    @Step("Нажатие на кнопку логина")
    public void clickLogin() {
        toLoginButton.click();
    }

    @Step("Нажатие на кнопку Булки")
    public void clickBuns() {
        buns.click();
    }

    @Step("Нажатие на кнопку Соусы")
    public void clickSauces() {
        sauces.click();
    }

    @Step("Нажатие на кнопку Начинки")
    public void clickFilling() {
        fillings.click();
    }

    @Step("Проверка перемещения к секции булок")
    public void isBunsSectionVisible() {
        bunsSectionHeader.isDisplayed();
    }

    @Step("Проверка перемещения к секции соусов")
    public void isSaucesSectionVisible() {
        saucesSectionHeader.isDisplayed();
    }

    @Step("Проверка перемещения к секции начинок")
    public void isFillingsSectionVisible() {
        fillingsSectionHeader.isDisplayed();
    }

    @Step("Проверка нахождения на странице Конструктор")
    public boolean isAtConstructorPage() {
        return buns.isDisplayed();
    }
}