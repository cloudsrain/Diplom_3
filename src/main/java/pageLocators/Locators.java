package pageLocators;

import org.openqa.selenium.By;

public class Locators {

    // Кнопки
    public static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    public static final By REGISTRATION_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    public static final By LOGIN_BUTTON = By.xpath(".//*[text()='Войти']");
    public static final By PROFILE_BUTTON = By.xpath(".//*[text()='Профиль']");
    public static final By MAIN_PAGE_LOGIN_BUTTON = By.xpath(".//*[text()='Войти в аккаунт']");
    public static final By LOGOUT_BUTTON = By.xpath(".//*[text()='Выход']");
    public static final By BUNS_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Булки']]");
    public static final By SAUCE_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Соусы']]");
    public static final By FILLINGS_BUTTON = By.xpath("//div[contains(@class, 'tab_tab__') and .//span[text()='Начинки']]");
    // Поля ввода
    public static final By NAME_FIELD = By.xpath("//label[text()='Имя']/following-sibling::input");
    public static final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    public static final By PASSWORD_FIELD = By.xpath("//input[@type='password']");
    // Различные элементы
    public static final By FORGET_PASSWORD_LINK =By.xpath(".//*[text()='Восстановить пароль']");
    public static final By REGISTRATION_LINK = By.xpath(".//*[text()='Зарегистрироваться']");
    public static final By WRONG_PASSWORD_ERROR = By.xpath(".//*[text()='Некорректный пароль']");
    public static final By LOGIN_HEADER_TEXT = By.xpath("//h2[text()='Вход']");
    public static final By BURGER_LOGO = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");
    public static final By CONSTRUCTOR = By.xpath(".//*[text()='Конструктор']");
    public static final By CONSTRUCTOR_HEADER = By.xpath(".//*[text()='Соберите бургер']");
    public static final String SELECTED_SECTION = "tab_tab_type_current__2BEPc";
}
