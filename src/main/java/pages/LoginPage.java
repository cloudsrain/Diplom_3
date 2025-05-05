package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageLocators.Locators;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку 'Войти'")
    public void clickOnLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ждём до 10 секунд
        wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_BUTTON)).click();
    }

    @Step("Переходим по ссылку 'Восстановить пароль'")
    public void clickOnForgetPasswordLink(){
        driver.findElement(Locators.FORGET_PASSWORD_LINK).click();
    }

    @Step("Переходим по ссылке 'Зарегистрироваться'")
    public void clickOnRegistrationLink() {
        driver.findElement(Locators.REGISTRATION_LINK).click();
    }

    @Step("Заполняем форму входа: email = {0}, пароль = {1}")
    public void fillingLoginForm(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_HEADER_TEXT));
        driver.findElement(Locators.EMAIL_FIELD).clear();
        driver.findElement(Locators.EMAIL_FIELD).sendKeys(email);
        driver.findElement(Locators.PASSWORD_FIELD).clear();
        driver.findElement(Locators.PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Успешный выход из аккаунта")
    public void successfulLogout (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_HEADER_TEXT));
        Assert.assertTrue("Заголовка нет на экране",driver.findElement(Locators.LOGIN_HEADER_TEXT).isDisplayed());
    }

}
