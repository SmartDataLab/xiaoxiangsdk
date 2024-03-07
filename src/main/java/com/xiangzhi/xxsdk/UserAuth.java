package com.xiangzhi.xxsdk;

public class UserAuth implements Auth {
    private String userKey;
    private String userSecret;

    public UserAuth(String userKey, String userSecret) {
        this.userKey = userKey;
        this.userSecret = userSecret;
    }

    @Override
    public String key() {
        return userKey;
    }

    @Override
    public String secret() {
        return userSecret;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }
}
