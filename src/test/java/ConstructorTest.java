import data.TestData;
import driver.DriverFactory;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.Duration;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        // Инициализация драйвера через фабрику
        driver = DriverFactory.createDriver("chrome");// "chrome" или "yandex"
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        // Открывается тестируемый сайт
        driver.get(TestData.BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Successful transition to buns section")
    @Description("Успешный переход к разделу 'Булки'")
    public void runSuccessfulTransitionToBunsTest(){
        mainPage.clickOnFillings();
        mainPage.clickOnBuns();
        mainPage.checkBunsIsSelected();
    }

    @Test
    @DisplayName("Successful transition to sauce section")
    @Description("Успешный переход к разделу 'Соусы'")
    public void runSuccessfulTransitionToSauceTest(){
        mainPage.clickOnSauce();
        mainPage.checkSauceIsSelected();
    }
    @Test
    @DisplayName("Successful transition to fillings section")
    @Description("Успешный переход к разделу 'Начинки'")
    public void runSuccessfulTransitionToFillingsTest (){
        mainPage.clickOnFillings();
        mainPage.checkFillingsIsSelected();
    }

    @After
    public void tearDown() {
        // Закрываем браузер после теста
        driver.quit();
    }

}
