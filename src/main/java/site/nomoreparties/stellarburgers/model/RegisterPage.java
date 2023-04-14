package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final By REGISTER_PAGE = By.xpath(".//*[text()='Регистрация']");
    private static final By NAME = By.xpath(".//fieldset[1]//input[@name='name']");
    private static final By EMAIL = By.xpath(".//fieldset[2]//input[@name='name']");
    private static final By PASSWORD = By.xpath(".//input[@name='Пароль']");
    private static final By REGISTER = By.xpath(".//*[text()='Зарегистрироваться']");
    private static final By INCORRECT_PASSWORD = By.xpath(".//*[text()='Некорректный пароль']");
    private static final By LOGIN = By.xpath(".//*[text()='Войти']");

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage waitUntilReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_PAGE));
        return this;
    }

    public RegisterPage fillInFields(String name, String email, String password) {
        driver.findElement(NAME).sendKeys(name);
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public RegisterPage register() {
        driver.findElement(REGISTER).click();
        return this;
    }

    public RegisterPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public RegisterPage login() {
        driver.findElement(LOGIN).click();
        return this;
    }

    public String returnErrorMessageForIncorrectPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD));
        return driver.findElement(INCORRECT_PASSWORD).getText();
    }
}
