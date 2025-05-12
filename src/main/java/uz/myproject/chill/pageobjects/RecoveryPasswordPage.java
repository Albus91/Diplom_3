package uz.myproject.chill.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoveryPasswordPage {

    public RecoveryPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[contains(text(),'Вспомнили пароль')]/a")
    private WebElement loginLink;

    @Step("Нажатие на ссылку к логину")
    public void clickLoginLink() {
        loginLink.click();
    }
}
