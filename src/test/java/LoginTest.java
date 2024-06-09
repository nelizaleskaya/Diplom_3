import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.config.BaseURI;
import org.example.extension.WebDriverConfig;
import org.example.pages.*;
import org.example.user.User;
import org.example.user.UserStep;
import org.example.config.CreateUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.config.PATH_URL.*;
import static org.example.config.Constant.WAIT_TIMEOUT;

public class LoginTest {

    private WebDriver driver;
    User user;
    public UserStep userStep;

    @Before
    public void setup() {
        RestAssured.baseURI = BaseURI.BASE_URI;
        user = CreateUser.createUser();
        UserStep.createUser(user);
        userStep = new UserStep();

        driver = WebDriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Login from the main page")
    public void loginOnMainPageGetSuccess() {
        driver.navigate().to(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Login from the profile")
    public void loginWithProfileGetSuccess() {
        driver.navigate().to(MAIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.clickProfileButton();
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Login from the register page")
    public void loginWithRegisterPageGetSuccess() {
        driver.navigate().to(REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        registerPage.clickLoginButtonUnderReg();
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @Test
    @DisplayName("Login from the reset password page")
    public void loginWithResetPasswordPageGetSuccess() {
        driver.navigate().to(RESET_PASSWORD_PAGE_URL);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        resetPasswordPage.clickLoginButtonUnderResetting();
        loginPage.loginUser(user);
        Assert.assertTrue(profilePage.btnProfileTabIsEnabled());
    }

    @After
    public void teardown() {
        driver.quit();
        userStep.deleteUser(userStep.getAccessToken(userStep.loginUser(user)));
    }
}
