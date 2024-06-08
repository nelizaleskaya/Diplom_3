package org.example.user;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.example.config.BaseURI.BASE_URI;
import static org.example.config.PATH_URL.*;

public class UserStep {
    public UserStep() {
        RestAssured.baseURI = BASE_URI;
    }
    public String accessToken;
    public UserCreds userCreds;
    @Step("Create an user")
    public static Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(AUTH_REGISTER_PATH);
        return response;
    }

    @Step("Get an user accessToken")
    public static Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(AUTH_LOGIN_PATH);
        return response;
    }
    public String getAccessToken (Response response) {
        userCreds = response.as(UserCreds.class);
        accessToken = userCreds.getAccessToken().split(" ")[1];
        return accessToken;
    }

    @Step("Delete an user")
    public void deleteUser(String accessToken) {
        if (accessToken != null)
            given()
                    .auth().oauth2(accessToken)
                    .when()
                    .delete(AUTH_USER_PATH);
    }
}
