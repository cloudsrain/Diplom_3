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

public class ProfileTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    Client client;

    @Before
    public void setUp() {
        // Инициализация ChromeDriver
        driver = DriverFactory.createDriver("yandex");  // "chrome" или "yandex"
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
    @DisplayName("Navigate to profile from header")
    @Description("Проверка, что после авторизации при клике на кнопку «Личный кабинет» происходит переход на страницу профиля")
    public void runNavigateToProfileFromHeaderTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }

    @Test
    @DisplayName("Return to constructor from profile page")
    @Description("Проверка перехода на страницу конструктора по кнопке «Конструктор» из личного кабинета")
    public void runReturnToConstructorFromProfileTest(){
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnConstructorButton();
        mainPage.successfulConstructorOpening();
    }

    @Test
    @DisplayName("Return to constructor from profile page 2")
    @Description("Проверка перехода на страницу конструктора по логотипу Stellar Burgers из личного кабинета")
    public void runReturnToConstructorFromProfileLogoTest(){
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnLogo();
        mainPage.successfulConstructorOpening();
    }

    @Test
    @DisplayName("Logout from profile page")
    @Description("Проверка, что при клике на кнопку «Выйти» в личном кабинете происходит корректный выход из аккаунта и переход на страницу входа")
    public void runLogoutFromProfileTest(){
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnLogoutButton();
        loginPage.successfulLogout();
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
