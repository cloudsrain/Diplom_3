package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    //ЛОКАТОРЫ
    private static final By REGISTRATION_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By NAME_FIELD = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD = By.xpath("//input[@type='password']");
    private static final By WRONG_PASSWORD_ERROR = By.xpath(".//*[text()='Некорректный пароль']");
    //ЛОКАТОРЫ

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Нажимаем на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Заполняем форму регистрации: имя = {0}, email = {1}, пароль = {2}")
    public void fillingRegistrationForm(String name, String email, String password) {
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(name);
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Проверяем появление ошибки при некорректном пароле")
    public void invalidPasswordError() {
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(WRONG_PASSWORD_ERROR));
        Assert.assertTrue("Ошибка 'Некорректный пароль' не появилась", error.isDisplayed());
    }
}
