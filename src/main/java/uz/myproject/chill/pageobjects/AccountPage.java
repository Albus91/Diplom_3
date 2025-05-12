package uz.myproject.chill.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/account' and @aria-current='page']")
    private WebElement accountHeaderLink;

    @FindBy(xpath = "//ul[contains(@class,'Account_list')]//button[normalize-space(.)='Выход']")
    private WebElement logoutButton;

    @Step("Проверка нахождения на странице личного кабинета")
    public void isAtAccountPage() {
        accountHeaderLink.isDisplayed();
    }

    @Step("Нажатие на кнопку выхода из аккаунта")
    public void clickLogout() {
        logoutButton.click();
    }
}