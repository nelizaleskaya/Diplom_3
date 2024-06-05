package org.example.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.config.PATH_URL;

import static io.restassured.RestAssured.given;

public class UserStep {
    @Step("Create user")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(PATH_URL.AUTH_REGISTER_PATH);
    }

    @Step("Login user")
    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(PATH_URL.AUTH_LOGIN_PATH);
    }

    @Step("Get user's accessToken")
    public static String getAccessToken(User user) {
        return loginUser(user).then().extract().path("accessToken");
    }

    @Step("Delete user")
    public static void deleteUser(String accessToken) {
        if (accessToken != null)
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(PATH_URL.AUTH_USER_PATH);
    }
}
