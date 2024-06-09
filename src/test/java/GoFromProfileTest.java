import io.qameta.allure.junit4.DisplayName;
import org.example.extension.WebDriverConfig;
import org.example.pages.MainPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.config.PATH_URL.MAIN_PAGE_URL;
import static org.example.config.BaseMessage.*;
import static org.example.config.Constant.WAIT_TIMEOUT;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class GoFromProfileTest {

    private WebDriver driver;
    private final String button;

    @Before
    public void setup() {
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(MAIN_PAGE_URL);
    }

    public GoFromProfileTest(String button) {
        this.button = button;
    }


    @Parameterized.Parameters(name = "Go from profile using {0} button")
    public static Object[] backToMainButtons() {
        return new Object[][]{
                {LOGO_BACK_TO_MAIN_PAGE},
                {CONSTRUCTOR_BACK_TO_MAIN},
        };
    }

    @Test
    @DisplayName("Go from the profile to main page")
    public void goFromProfileToMain() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickProfileButton();
        mainPage.backToMainPage(button);

        MatcherAssert.assertThat(mainPage.getCreateBurgerTextFromHeader(), equalTo(MAKE_BURGER_MESSAGE));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
