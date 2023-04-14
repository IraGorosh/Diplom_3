package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By LOGIN = By.xpath(".//*[text()='Войти']");

    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public PasswordRecoveryPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public PasswordRecoveryPage login() {
        driver.findElement(LOGIN).click();
        return this;
    }
}
