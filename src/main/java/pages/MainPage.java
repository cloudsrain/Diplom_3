package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageLocators.Locators;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку 'Личный Кабинет'")
    public void clickOnPersonalAccountButton() {
        driver.findElement(Locators.PERSONAL_ACCOUNT_BUTTON).click();
    }

    @Step("Нажимаем на кнопку 'Войти в аккаунт'")
    public void clickOnMainPageLoginButton(){
        driver.findElement(Locators.MAIN_PAGE_LOGIN_BUTTON).click();
    }

    @Step("Нажимаем на 'Булки', 'Соусы', или 'Начинки'")
    public void clickOnBurgerParts (By button){
        driver.findElement(button).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(button, "class", "tab_tab_type_current__2BEPc"));
    }

    public void checkSectionIsSelected(By button, String selectedClass) {
        // Ждем появления нужного класса на элементе
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.attributeContains(button, "class", selectedClass));

        WebElement element = driver.findElement(button);
        String classAttr = element.getDomAttribute("class");

        Assert.assertNotNull("Атрибут 'class' отсутствует у элемента", classAttr);
        System.out.println(classAttr);
        Assert.assertTrue("Ожидалось, что элемент будет выбран (класс содержит '" + selectedClass + "')",
                classAttr.contains(selectedClass));
    }

    @Step("Проверка успешного перехода в раздел 'Коструктор'")
    public void successfulConstructorOpening (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.CONSTRUCTOR_HEADER));
        Assert.assertTrue("Кнопки 'Профиль' нет на экране", driver.findElement(Locators.CONSTRUCTOR_HEADER).isDisplayed());
    }

}
