package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageLocators.Locators;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Нажимаем на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationButton() {
        driver.findElement(Locators.REGISTRATION_BUTTON).click();
    }

    @Step("Заполняем форму регистрации: имя = {0}, email = {1}, пароль = {2}")
    public void fillingRegistrationForm(String name, String email, String password) {
        driver.findElement(Locators.NAME_FIELD).clear();
        driver.findElement(Locators.NAME_FIELD).sendKeys(name);
        driver.findElement(Locators.EMAIL_FIELD).clear();
        driver.findElement(Locators.EMAIL_FIELD).sendKeys(email);
        driver.findElement(Locators.PASSWORD_FIELD).clear();
        driver.findElement(Locators.PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Проверяем появление ошибки при некорректном пароле")
    public void invalidPasswordError() {
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.WRONG_PASSWORD_ERROR));
        Assert.assertTrue("Ошибка 'Некорректный пароль' не появилась", error.isDisplayed());
    }
}
