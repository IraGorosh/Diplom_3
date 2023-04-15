package site.nomoreparties.stellarburgers.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.User;

public class UserLoginSteps {
    public static final String NAVIGATION_TO_MAIN_PAGE_WAS_EXPECTED = "Не произошел переход на главную страницу, кнопка оформления заказа не отображается";

    public static void assertToGoToTheMainPageAfterLogin(WebDriver driver, User user) {
        new LoginPage(driver)
                .waitUntilReady()
                .fillInCredentials(user.getEmail(), user.getPassword())
                .login();
        String buttonName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(NAVIGATION_TO_MAIN_PAGE_WAS_EXPECTED, "Оформить заказ", buttonName);
    }

    public static void deleteUserViaApi(User user) {
        User userCredentials = new User(user.getEmail(), user.getPassword());
        UserClient userClient = new UserClient();
        String accessToken = userClient
                .login(userCredentials)
                .extract()
                .path("accessToken");
        userClient.delete(accessToken);
    }

    public static void deleteLoggedInUser(WebDriver driver) {
        LocalStorage local = ((WebStorage) driver).getLocalStorage();
        UserClient userClient = new UserClient();
        userClient.delete(local.getItem("accessToken"));
    }
}
