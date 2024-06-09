package org.example.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.user.User;

import static org.example.config.Constant.*;

public class CreateUser {

    public static String createUserName() {
        return RandomStringUtils.randomAlphabetic(COUNT_BY_RANDOM);
    }

    public static String createUserEmail() {
        return RandomStringUtils.randomAlphabetic(COUNT_BY_RANDOM) + DOMAIN_EMAIL_NAME;
    }

    public static String createUserPassword() {
        return RandomStringUtils.randomAlphabetic(COUNT_BY_RANDOM);
    }

    public static String createWrongUserPassword() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static User createUser() {
        String name = createUserName();
        String email = createUserEmail();
        String password = createUserPassword();
        return new User(name, email, password);
    }
}
