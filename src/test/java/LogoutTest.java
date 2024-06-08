import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.config.BaseURI;
import org.example.extension.WebDriverConfig;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.example.user.User;
import org.example.user.UserStep;
import org.example.config.CreateUser;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.config.PATH_URL.MAIN_PAGE_URL;
import static org.example.config.BaseMessage.ENTRANCE_MESSAGE;
import static org.example.config.Constant.WAIT_TIMEOUT;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogoutTest {
    private WebDriver driver;
    User user;

    UserStep userStep;

    @Before
    public void setup() {
        RestAssured.baseURI = BaseURI.BASE_URI;
        user = CreateUser.createUser();
        UserStep.createUser(user);
        userStep = new UserStep();
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(MAIN_PAGE_URL);

    }

    @Test
    @DisplayName("Log out and return to the login page")
    public void logoutGetSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.clickProfileButton();
        loginPage.loginUser(user);
        profilePage.clickExitButton();
        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo(ENTRANCE_MESSAGE));
    }

    @After
    public void teardown() {
        driver.quit();
        userStep.deleteUser(userStep.getAccessToken(userStep.loginUser(user)));
    }
}
