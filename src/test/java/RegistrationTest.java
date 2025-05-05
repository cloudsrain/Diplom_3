import client.Client;
import data.TestData;
import driver.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegistrationPage;

import java.time.Duration;

public class RegistrationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registration;
    private ProfilePage profilePage;
    UserLogin userLogin = new UserLogin(TestData.EMAIL, TestData.PASSWORD);
    boolean isUserCreated = false;

    @Before
    public void setUp() {
        // Инициализация ChromeDriver
        driver = DriverFactory.createDriver("yandex");  // "chrome" или "yandex"
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registration = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
        // Открывается тестируемый сайт
        driver.get(TestData.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @Test
    @DisplayName("Successful registration with valid data")
    @Description("Успешная регитсрация с валидными данными")
    public void runSuccessfulRegistrationTest (){
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        registration.fillingRegistrationForm(TestData.NAME,TestData.EMAIL, TestData.PASSWORD);
        registration.clickOnRegistrationButton();
        loginPage.fillingLoginForm(TestData.EMAIL, TestData.PASSWORD);
        loginPage.clickOnLoginButton();
        mainPage.clickOnPersonalAccountButton();
        profilePage.successfulLogin();
        isUserCreated = true;
    }

    @Test
    @DisplayName("Registration with invalid password")
    @Description("Регистрация с использованием слишком короткого пароля, ожидается сообщение об ошибке")
    public void runInvalidRegistrationWithShortPasswordTest(){
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        registration.fillingRegistrationForm(TestData.NAME, TestData.EMAIL, TestData.INVALID_PASSWORD);
        registration.clickOnRegistrationButton();
        registration.invalidPasswordError();
    }

    @After
    public void tearDown() {
        // Закрываем браузер после теста
        driver.quit();
        Client client = new Client();
        if (isUserCreated){
            ValidatableResponse response = client.loginUser(userLogin);
            String accessToken = Client.successfulCreation(response);
            client.deleteUser(accessToken);
        }

    }
}
