package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final By LOGIN_PAGE_HEADER = By.xpath(".//*[text()='Вход']");
    private static final By REGISTER = By.xpath(".//*[text()='Зарегистрироваться']");
    private static final By EMAIL = By.xpath(".//input[@name='name']");
    private static final By PASSWORD = By.xpath(".//input[@name='Пароль']");
    private static final By LOGIN = By.xpath(".//button[text()='Войти']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage waitUntilReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PAGE_HEADER));
        return this;
    }

    public LoginPage register() {
        driver.findElement(REGISTER).click();
        return this;
    }

    public LoginPage fillInCredentials(String email, String password) {
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public LoginPage login() {
        driver.findElement(LOGIN).click();
        return this;
    }

    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public String returnPageName() {
        return driver.findElement(LOGIN_PAGE_HEADER).getText();
    }
}
