package site.nomoreparties.stellarburgers.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.User;

public class UserLoginSteps {
    public static final String ERROR_MESSAGE = "Не произошел переход на главную страницу, кнопка оформления заказа не отображается";

    public static void assertToGoToTheMainPageAfterLogin(WebDriver driver, User user) {
        new LoginPage(driver)
                .waitUntilReady()
                .fillInCredentials(user.getEmail(), user.getPassword())
                .login();
        String buttonName = new MainPage(driver)
                .waitUntilReady()
                .returnButtonPlaceAnOrderName();
        Assert.assertEquals(ERROR_MESSAGE, "Оформить заказ", buttonName);
    }

    public static void assertThatUserDeletesAfterLogin(User user) {
        User userCredentials = new User(user.getEmail(), user.getPassword());
        UserClient userClient = new UserClient();
        String accessToken = userClient
                .login(userCredentials)
                .extract()
                .path("accessToken");
        userClient.delete(accessToken);
    }
}
