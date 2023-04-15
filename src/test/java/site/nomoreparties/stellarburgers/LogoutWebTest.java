package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.ProfilePage;
import site.nomoreparties.stellarburgers.model.User;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.PersonalAccountSteps.loginAndGoToPersonalAccount;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.deleteUserViaApi;
import static site.nomoreparties.stellarburgers.webDriver.driverFactory.createChromeDriver;

public class LogoutWebTest {
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
        deleteUserViaApi(user);
        driver.quit();
    }

    @Test
    @DisplayName("Check successful logout by user")
    public void userCanLogout() {
        loginAndGoToPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .logout();
        String pageName = new LoginPage(driver)
                .waitUntilReady()
                .returnPageName();
        Assert.assertEquals("Не произошел переход на страницу входа", "Вход", pageName);
    }
}
