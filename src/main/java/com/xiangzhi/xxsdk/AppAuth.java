package com.xiangzhi.xxsdk;

public class AppAuth implements Auth {
    private String appKey;
    private String appSecret;

    public AppAuth(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    @Override
    public String key() {
        return appKey;
    }

    @Override
    public String secret() {
        return appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
