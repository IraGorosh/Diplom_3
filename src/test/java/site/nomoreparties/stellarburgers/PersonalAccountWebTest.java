package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.ProfilePage;
import site.nomoreparties.stellarburgers.model.User;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.PersonalAccountSteps.loginAndGoToPersonalAccount;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.NAVIGATION_TO_MAIN_PAGE_WAS_EXPECTED;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.deleteLoggedInUser;
import static site.nomoreparties.stellarburgers.webDriver.driverFactory.createChromeDriver;

public class PersonalAccountWebTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        driver = createChromeDriver();
        userClient = new UserClient();
        user = new User(getString(16), getEmail(), getString(16));
        userClient.create(user);
    }

    @After
    public void cleanUp() {
        deleteLoggedInUser(driver);
        driver.quit();
    }

    @Test
    @DisplayName("Check successful transition to personal account section")
    public void checkClickToGoToPersonalAccount() {
        loginAndGoToPersonalAccount(driver, user);
        String profileElementName = new ProfilePage(driver)
                .waitUntilReady()
                .returnProfileElementName();
        Assert.assertEquals("Элемент профиля не найден на странице", "Профиль", profileElementName);
    }

    @Test
    @DisplayName("Check successful transition to construction section from personal account")
    public void checkClickConstructorInThePersonalAccount() {
        loginAndGoToPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .clickOnTheConstructor();
        String placeAnOrderName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(NAVIGATION_TO_MAIN_PAGE_WAS_EXPECTED, "Оформить заказ", placeAnOrderName);
    }

    @Test
    @DisplayName("Check successful transition to main page from personal account by click on the Stellar Burgers")
    public void checkClickStellarBurgersLogoInThePersonalAccount() {
        loginAndGoToPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .clickOnTheStellarBurgers();
        String placeAnOrderName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(ERROR_MESSAGE, "Оформить заказ", placeAnOrderName);
    }
}
