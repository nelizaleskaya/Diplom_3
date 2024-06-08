import io.qameta.allure.junit4.DisplayName;
import org.example.extension.WebDriverConfig;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.example.pages.RegisterPage;
import org.example.user.User;
import org.example.user.UserStep;
import org.example.config.CreateUser;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.config.PATH_URL.REGISTER_PAGE_URL;
import static org.example.config.BaseMessage.*;
import static org.example.config.Constant.WAIT_TIMEOUT;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterTest {
    private WebDriver driver;
    User user;
    public UserStep userStep;

    @Before
    public void setup() {
        user = CreateUser.createUser();
        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(REGISTER_PAGE_URL);
    }

    @Test
    @DisplayName("Register a new user with a short password")
    public void registerNewUserWithShortPasswordGetError() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(CreateUser.createWrongUserPassword());
        registerPage.clickRegisterButton();
        MatcherAssert.assertThat(registerPage.getInvalidPasswordText(), equalTo(ERROR_PASSWORD_MESSAGE));
    }

    @Test
    @DisplayName("Register a new user with valid creds")
    public void registerNewUserGetSuccess() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        userStep = new UserStep();
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();
        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo(ENTRANCE_MESSAGE));
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
        userStep.deleteUser(userStep.getAccessToken(userStep.loginUser(user)));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
