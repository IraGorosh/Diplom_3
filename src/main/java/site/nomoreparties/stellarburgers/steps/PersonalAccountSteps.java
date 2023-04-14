package site.nomoreparties.stellarburgers.steps;

import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.User;

public class PersonalAccountSteps {

    public static void getIntoPersonalAccount(WebDriver driver, User user) {
        new LoginPage(driver)
                .open()
                .fillInCredentials(user.getEmail(), user.getPassword())
                .login();
        new MainPage(driver)
                .waitUntilReady()
                .clickOnThePersonalAccount();
    }
}
