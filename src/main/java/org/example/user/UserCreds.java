package org.example.user;

public class UserCreds {
    private String success;
    private String accessToken;
    private String refreshToken;
    private UserResPartUser user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserResPartUser getUser() {
        return user;
    }

    public void setUser(UserResPartUser user) {
        this.user = user;
    }
}
