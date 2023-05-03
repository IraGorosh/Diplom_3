package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private static final By PROFILE = By.xpath(".//*[text()='Профиль']");
    private static final By CONSTRUCTOR = By.xpath(".//*[text()='Конструктор']");
    private static final By STELLAR_BURGERS = By.className("AppHeader_header__logo__2D0X2");
    private static final By LOGOUT = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProfilePage waitUntilReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE));
        return this;
    }

    public ProfilePage clickOnTheConstructor() {
        driver.findElement(CONSTRUCTOR).click();
        return this;
    }

    public ProfilePage clickOnTheStellarBurgers() {
        driver.findElement(STELLAR_BURGERS).click();
        return this;
    }

    public ProfilePage logout() {
        driver.findElement(LOGOUT).click();
        return this;
    }

    public String returnProfileElementName() {
        return driver.findElement(PROFILE).getText();
    }
}
