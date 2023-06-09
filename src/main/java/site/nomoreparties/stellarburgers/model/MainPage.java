package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final By LOGIN = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By PLACE_AN_ORDER = By.xpath(".//button[text()='Оформить заказ']");
    private static final By PERSONAL_ACCOUNT = By.xpath("//*[text()='Личный Кабинет']");
    private static final By BUNS = By.xpath("//span[text()='Булки']");
    private static final By SAUCES = By.xpath("//span[text()='Соусы']");
    private static final By FILLINGS = By.xpath("//span[text()='Начинки']");
    private static final By SELECTED_SECTION_NAME = By.className("tab_tab_type_current__2BEPc");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage login() {
        driver.findElement(LOGIN).click();
        return this;
    }

    public MainPage waitUntilReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PLACE_AN_ORDER));
        return this;
    }

    public MainPage clickOnThePersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT).click();
        return this;
    }

    public MainPage clickOnTheBuns() {
        driver.findElement(BUNS).click();
        return this;
    }

    public MainPage clickOnTheSauces() {
        driver.findElement(SAUCES).click();
        return this;
    }

    public MainPage clickOnTheFillings() {
        driver.findElement(FILLINGS).click();
        return this;
    }

    public String returnNameOfTheSelectedSection() {
        return driver.findElement(SELECTED_SECTION_NAME).getText();
    }

    public String returnButtonPlaceAnOrderName() {
        return driver.findElement(PLACE_AN_ORDER).getText();
    }
}
