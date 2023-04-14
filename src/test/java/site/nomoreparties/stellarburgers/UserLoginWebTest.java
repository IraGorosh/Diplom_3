package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.*;

import static site.nomoreparties.stellarburgers.model.DataGenerator.getEmail;
import static site.nomoreparties.stellarburgers.model.DataGenerator.getString;
import static site.nomoreparties.stellarburgers.steps.UserLoginSteps.assertToGoToTheMainPageAfterLogin;

public class UserLoginWebTest {
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
    @DisplayName("Check successful login through login-to-account button")
    public void loginUsingTheLoginToAccountButtonOnTheMainPage() {
        new MainPage(driver)
                .open()
                .login();
        assertToGoToTheMainPageAfterLogin(driver, user);
    }

    @Test
    @DisplayName("Check successful login through personal-account button")
    public void loginThroughTheButtonPersonalAccount() {
        new MainPage(driver)
                .open()
                .clickOnThePersonalAccount();
        assertToGoToTheMainPageAfterLogin(driver, user);
    }

    @Test
    @DisplayName("Check successful login via registration form")
    public void loginViaRegistrationForm() {
        new RegisterPage(driver)
                .open()
                .login();
        assertToGoToTheMainPageAfterLogin(driver, user);
    }

    @Test
    @DisplayName("Check successful login via password recovery form")
    public void loginViaPasswordRecoveryForm() {
        new PasswordRecoveryPage(driver)
                .open()
                .login();
        assertToGoToTheMainPageAfterLogin(driver, user);
    }
}
