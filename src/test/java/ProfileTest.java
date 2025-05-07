import client.Client;
import com.github.javafaker.Faker;
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
import java.util.Locale;

public class ProfileTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private Client client;

    private final Faker faker = new Faker(new Locale("en"));
    private String email;
    private String password;
    private String name;
    private UserLogin userLogin;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver("chrome");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // Генерация пользователя
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 12);  // валидный пароль
        name = faker.name().firstName();

        client = new Client();
        User user = new User(email, password, name);
        client.createUser(user);
        userLogin = new UserLogin(email, password);

        driver.get(TestData.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Navigate to profile from header")
    @Description("Проверка, что после авторизации при клике на кнопку «Личный кабинет» происходит переход на страницу профиля")
    public void runNavigateToProfileFromHeaderTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(email, password);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
    }

    @Test
    @DisplayName("Return to constructor from profile page")
    @Description("Проверка перехода на страницу конструктора по кнопке «Конструктор» из личного кабинета")
    public void runReturnToConstructorFromProfileTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(email, password);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnConstructorButton();
        mainPage.successfulConstructorOpening();
    }

    @Test
    @DisplayName("Return to constructor from profile page 2")
    @Description("Проверка перехода на страницу конструктора по логотипу Stellar Burgers из личного кабинета")
    public void runReturnToConstructorFromProfileLogoTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(email, password);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnLogo();
        mainPage.successfulConstructorOpening();
    }

    @Test
    @DisplayName("Logout from profile page")
    @Description("Проверка, что при клике на кнопку «Выйти» в личном кабинете происходит корректный выход из аккаунта и переход на страницу входа")
    public void runLogoutFromProfileTest() {
        mainPage.clickOnMainPageLoginButton();
        loginPage.fillingLoginForm(email, password);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.clickOnLogoutButton();
        loginPage.successfulLogout();
    }

    @After
    public void tearDown() {
        driver.quit();
        ValidatableResponse response = client.loginUser(userLogin);
        String accessToken = Client.successfulCreation(response);
        client.deleteUser(accessToken);
    }
}
