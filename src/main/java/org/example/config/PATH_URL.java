package org.example.config;

import static org.example.config.BaseURI.DOMAIN;

public class PATH_URL {

    //    создание юзера POST
    public static final String AUTH_REGISTER_PATH = "/api/auth/register";
    //    логин юзера POST
    public static final String AUTH_LOGIN_PATH = "/api/auth/login";
    //    удаление юзера POST
    public static final String AUTH_USER_PATH = "/api/auth/user";
    public static final String MAIN_PAGE_URL = DOMAIN + "/";
    public static final String REGISTER_PAGE_URL = DOMAIN + "/register";
    public static final String RESET_PASSWORD_PAGE_URL = DOMAIN + "/forgot-password";
}
