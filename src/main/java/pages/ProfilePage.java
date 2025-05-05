package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageLocators.Locators;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на Кнопку 'Конструктор' в шапке сайта")
    public void clickOnConstructorButton(){
        driver.findElement(Locators.CONSTRUCTOR).click();
    }

    @Step("Нажимаем на логотип в шапке сайта")
    public void clickOnLogo(){
        driver.findElement(Locators.BURGER_LOGO).click();
    }

    @Step("Нажимаем на кнопку 'Выход'")
    public void clickOnLogoutButton (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.PROFILE_BUTTON));
        driver.findElement(Locators.LOGOUT_BUTTON).click();
    }

    @Step("Проврека успешного входа в аккаунт")
    public void successfulLogin(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.PROFILE_BUTTON));
        Assert.assertTrue("Кнопки 'Профиль' нет на экране", driver.findElement(Locators.PROFILE_BUTTON).isDisplayed());
    }

}
