package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.model.MainPage;

import static site.nomoreparties.stellarburgers.webDriver.driverFactory.createChromeDriver;

public class ConstructorWebTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = createChromeDriver();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

    @Test
    @DisplayName("Check successful transition to Buns section")
    public void checkTransitionToBuns() {
        String bunsSectionName = new MainPage(driver)
                .open()
                .clickOnTheFillings()
                .clickOnTheBuns()
                .returnBunsSectionName();
        Assert.assertEquals("Не произошел переход на секцию Булок", "Булки", bunsSectionName);
    }

    @Test
    @DisplayName("Check successful transition to Sauces section")
    public void checkTransitionToSauces() {
        String saucesSectionName = new MainPage(driver)
                .open()
                .clickOnTheSauces()
                .returnSaucesSectionName();
        Assert.assertEquals("Не произошел переход на секцию Соусов", "Соусы", saucesSectionName);
    }

    @Test
    @DisplayName("Check successful transition to Fillings section")
    public void checkTransitionToFillings() {
        String fillingsSectionName = new MainPage(driver)
                .open()
                .clickOnTheFillings()
                .returnFillingsSectionName();
        Assert.assertEquals("Не произошел переход на секцию Начинок", "Начинки", fillingsSectionName);
    }
}
