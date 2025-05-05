package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrew kan\\.cache\\selenium\\chromedriver\\win64\\131.0.6778.204\\chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrew kane\\.cache\\selenium\\chromedriver\\win64\\131.0.6778.204\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\andre\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        return new ChromeDriver(options);
    }

}
