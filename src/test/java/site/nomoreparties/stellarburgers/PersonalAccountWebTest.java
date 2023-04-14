package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.ProfilePage;
import site.nomoreparties.stellarburgers.model.User;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.PersonalAccountSteps.getIntoPersonalAccount;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.ERROR_MESSAGE;

public class PersonalAccountWebTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        userClient = new UserClient();
        user = new User(getString(16), getEmail(), getString(16));
        userClient.create(user);
    }

    @After
    public void cleanUp() {
        LocalStorage local = ((WebStorage) driver).getLocalStorage();
        userClient.delete(local.getItem("accessToken"));
        driver.quit();
    }

    @Test
    @DisplayName("Check successful transition to personal account section")
    public void checkClickToGoToPersonalAccount() {
        getIntoPersonalAccount(driver, user);
        String profileElementName = new ProfilePage(driver)
                .waitUntilReady()
                .returnProfileElementName();
        Assert.assertEquals("Элемент профиля не найден на странице", "Профиль", profileElementName);
    }

    @Test
    @DisplayName("Check successful transition to construction section from personal account")
    public void checkClickConstructorInThePersonalAccount() {
        getIntoPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .clickOnTheConstructor();
        String placeAnOrderName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(ERROR_MESSAGE, "Оформить заказ", placeAnOrderName);
    }

    @Test
    @DisplayName("Check successful transition to main page from personal account by click on the Stellar Burgers")
    public void checkClickStellarBurgersLogoInThePersonalAccount() {
        getIntoPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .clickOnTheStellarBurgers();
        String placeAnOrderName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(ERROR_MESSAGE, "Оформить заказ", placeAnOrderName);
    }
}
