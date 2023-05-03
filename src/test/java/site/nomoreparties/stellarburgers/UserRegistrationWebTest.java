package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.RegisterPage;
import site.nomoreparties.stellarburgers.model.User;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.deleteUserViaApi;
import static site.nomoreparties.stellarburgers.webDriver.driverFactory.createChromeDriver;

public class UserRegistrationWebTest {
    private WebDriver driver;
    private User user;
    @Before
    public void setUp() {
        driver = createChromeDriver();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

    @Test
    @DisplayName("Check successful registration with valid data")
    public void userCanRegisterWithValidData() {
        user = new User(getString(16), getEmail(), getString(16));
        new MainPage(driver)
                .open()
                .login();
        new LoginPage(driver)
                .waitUntilReady()
                .register();
        new RegisterPage(driver)
                .waitUntilReady()
                .fillInFields(user.getName(), user.getEmail(), user.getPassword())
                .register();
        String pageName = new LoginPage(driver)
                .waitUntilReady()
                .returnPageName();
        Assert.assertEquals("Не произошел переход на страницу входа", "Вход", pageName);

        deleteUserViaApi(user);
    }

    @Test
    @DisplayName("Check that registration with password less than 6 characters is impossible")
    public void userCannotRegisterWithPasswordLessThanSixCharacters() {
        new MainPage(driver)
                .open()
                .login();
        new LoginPage(driver)
                .waitUntilReady()
                .register();
        String errorMessage = new RegisterPage(driver)
                .waitUntilReady()
                .fillInFields(getString(16), getEmail(), getString(5))
                .register()
                .returnErrorMessageForIncorrectPassword();
        Assert.assertEquals("Текст ошибки не появился на экране", "Некорректный пароль", errorMessage);
    }
}
