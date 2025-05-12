package uz.myproject.chill.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()='Конструктор']/ancestor::a")
    private WebElement constructorButton;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement profileLink;

    @FindBy(xpath = "//div[contains(@class,'AppHeader_header__logo')]/a")
    private WebElement logoLink;

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructor() {
        constructorButton.click();
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void clickProfile() {
        profileLink.click();
    }

    @Step("Нажатие на лого")
    public void clickLogo() {
        logoLink.click();
    }
}