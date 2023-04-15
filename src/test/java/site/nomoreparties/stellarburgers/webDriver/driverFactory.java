package site.nomoreparties.stellarburgers.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverFactory {

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        //https://stackoverflow.com/a/75686229
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
