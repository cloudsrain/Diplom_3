import client.Client;
import data.TestData;
import driver.DriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import model.User;
import model.UserLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    Client client;


    @Before
    public void setUp() {
        // Инициализация ChromeDriver
        driver = DriverFactory.createDriver("chrome");  // "chrome" или "yandex"
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        //Создаем пользователя через API запрос
        client = new Client();
        User user = new User(TestData.EMAIL, TestData.PASSWORD, TestData.NAME);
        client.createUser(user);
        // Открывается тестируемый сайт
        driver.get(TestData.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    @DisplayName("Successful login by main page login button")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void runLoginViaMainPageButtonTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }

    @Test
    @DisplayName("Successful login by personal account button")
    @Description("Вход через кнопку «Личный кабинет»")
    public void runLoginViaPersonalAccountButtonTest() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }

    @Test
    @DisplayName("Successful login by registration form button")
    @Description("Вход через кнопку в форме регистрации")
    public void runLoginViaRegistrationFormButtonTest() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        loginPage.clickOnLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }

    @Test
    @DisplayName("Successful login by forget password form")
    @Description("Вход через кнопку в форме восстановления пароля")
    public void runLoginViaPasswordRecoveryFormTest() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnForgetPasswordLink();
        loginPage.clickOnLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }


    @After
    public void tearDown() {
        // Закрываем браузер после теста
        driver.quit();
        UserLogin userLogin = new UserLogin(TestData.EMAIL, TestData.PASSWORD);
        ValidatableResponse response = client.loginUser(userLogin);
        String accessToken = Client.successfulCreation(response);
        client.deleteUser(accessToken);
    }
}
