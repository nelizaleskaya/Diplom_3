import io.qameta.allure.junit4.DisplayName;
import org.example.extension.WebDriverConfig;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.config.PATH_URL.MAIN_PAGE_URL;
import static org.example.config.Constant.WAIT_TIMEOUT;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Go to Buns tab")
    public void SwitchToBunsTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        Assert.assertTrue(mainPage.btnBunsIsEnabled());
    }

    @Test
    @DisplayName("Go to Sauces tab")
    public void SwitchToSaucesTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        Assert.assertTrue(mainPage.btnSaucesIsEnabled());
    }

    @Test
    @DisplayName("Go to Fillings tab")
    public void SwitchToFillingsTabGetSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        Assert.assertTrue(mainPage.btnFillingsIsEnabled());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
