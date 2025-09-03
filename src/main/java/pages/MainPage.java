package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    //ЛОКАТОРЫ
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By MAIN_PAGE_LOGIN_BUTTON = By.xpath(".//*[text()='Войти в аккаунт']");
    private static final By BUNS_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Булки']]");
    private static final By SAUCE_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Соусы']]");
    private static final By FILLINGS_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Начинки']]");
    private static final By CONSTRUCTOR_HEADER = By.xpath(".//*[text()='Соберите бургер']");
    private static final String SELECTED_SECTION = "tab_tab_type_current__2BEPc";
    //ЛОКАТОРЫ

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку 'Личный Кабинет'")
    public void clickOnPersonalAccountButton() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
    }

    @Step("Нажимаем на кнопку 'Войти в аккаунт'")
    public void clickOnMainPageLoginButton(){
        driver.findElement(MAIN_PAGE_LOGIN_BUTTON).click();
    }

    @Step("Нажимаем на 'Булки', 'Соусы', или 'Начинки'")
    public void clickOnBuns (){
        driver.findElement(BUNS_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(BUNS_BUTTON, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Нажимаем на 'Булки', 'Соусы', или 'Начинки'")
    public void clickOnSauce (){
        driver.findElement(SAUCE_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(SAUCE_BUTTON, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Нажимаем на 'Булки', 'Соусы', или 'Начинки'")
    public void clickOnFillings (){
        driver.findElement(FILLINGS_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(FILLINGS_BUTTON, "class", "tab_tab_type_current__2BEPc"));
    }

    public void checkBunsIsSelected() {
        // Ждем появления нужного класса на элементе
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.attributeContains(BUNS_BUTTON, "class", SELECTED_SECTION));

        WebElement element = driver.findElement(BUNS_BUTTON);
        String classAttr = element.getDomAttribute("class");

        Assert.assertNotNull("Атрибут 'class' отсутствует у элемента", classAttr);
        System.out.println(classAttr);
        Assert.assertTrue("Ожидалось, что элемент будет выбран (класс содержит '" + SELECTED_SECTION + "')",
                classAttr.contains(SELECTED_SECTION));
    }

    public void checkSauceIsSelected() {
        // Ждем появления нужного класса на элементе
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.attributeContains(SAUCE_BUTTON, "class", SELECTED_SECTION));

        WebElement element = driver.findElement(SAUCE_BUTTON);
        String classAttr = element.getDomAttribute("class");

        Assert.assertNotNull("Атрибут 'class' отсутствует у элемента", classAttr);
        System.out.println(classAttr);
        Assert.assertTrue("Ожидалось, что элемент будет выбран (класс содержит '" + SELECTED_SECTION + "')",
                classAttr.contains(SELECTED_SECTION));
    }

    public void checkFillingsIsSelected() {
        // Ждем появления нужного класса на элементе
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.attributeContains(FILLINGS_BUTTON, "class", SELECTED_SECTION));

        WebElement element = driver.findElement(FILLINGS_BUTTON);
        String classAttr = element.getDomAttribute("class");

        Assert.assertNotNull("Атрибут 'class' отсутствует у элемента", classAttr);
        System.out.println(classAttr);
        Assert.assertTrue("Ожидалось, что элемент будет выбран (класс содержит '" + SELECTED_SECTION + "')",
                classAttr.contains(SELECTED_SECTION));
    }

    @Step("Проверка успешного перехода в раздел 'Коструктор'")
    public void successfulConstructorOpening (){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCTOR_HEADER));
        Assert.assertTrue("Кнопки 'Профиль' нет на экране", driver.findElement(CONSTRUCTOR_HEADER).isDisplayed());
    }

}
