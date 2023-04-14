package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.ProfilePage;
import site.nomoreparties.stellarburgers.model.User;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.PersonalAccountSteps.getIntoPersonalAccount;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.assertThatUserDeletesAfterLogin;

public class LogoutWebTest {
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
        assertThatUserDeletesAfterLogin(user);
        driver.quit();
    }

    @Test
    @DisplayName("Check successful logout by user")
    public void userCanLogout() {
        getIntoPersonalAccount(driver, user);
        new ProfilePage(driver)
                .waitUntilReady()
                .logout();
        String pageName = new LoginPage(driver)
                .waitUntilReady()
                .returnPageName();
        Assert.assertEquals("Не произошел переход на страницу входа", "Вход", pageName);
    }
}
