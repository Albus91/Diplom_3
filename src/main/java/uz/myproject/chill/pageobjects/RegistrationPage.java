package uz.myproject.chill.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameInput;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//label[text()='Пароль']/following-sibling::input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(),'Зарегистрироваться')]")
    private WebElement registerButton;

    @FindBy(css = "p.input__error")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//p[contains(text(),'Уже зарегистрированы')]/a")
    private WebElement loginLink;

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        registerButton.click();
    }

    @Step("Ошибка 'Неверный пароль'")
    public void getPasswordErrorMessage() {
        passwordErrorMessage.getText();
    }

    @Step("Нажатие на ссылку на логин со страницы регистрации")
    public void clickLoginLink() {
        loginLink.click();
    }
}
