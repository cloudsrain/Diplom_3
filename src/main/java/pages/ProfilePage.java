package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    //ЛОКАТОРЫ
    private static final By PROFILE_BUTTON = By.xpath(".//*[text()='Профиль']");
    private static final By LOGOUT_BUTTON = By.xpath(".//*[text()='Выход']");
    private static final By BURGER_LOGO = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");
    private static final By CONSTRUCTOR = By.xpath(".//*[text()='Конструктор']");
    //ЛОКАТОРЫ

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на Кнопку 'Конструктор' в шапке сайта")
    public void clickOnConstructorButton(){
        driver.findElement(CONSTRUCTOR).click();
    }

    @Step("Нажимаем на логотип в шапке сайта")
    public void clickOnLogo(){
        driver.findElement(BURGER_LOGO).click();
    }

    @Step("Нажимаем на кнопку 'Выход'")
    public void clickOnLogoutButton (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
    }

    @Step("Проврека успешного входа в аккаунт")
    public void successfulLogin(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_BUTTON));
        Assert.assertTrue("Кнопки 'Профиль' нет на экране", driver.findElement(PROFILE_BUTTON).isDisplayed());
    }

}
