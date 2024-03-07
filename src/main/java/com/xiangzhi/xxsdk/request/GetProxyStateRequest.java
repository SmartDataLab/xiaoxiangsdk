package com.xiangzhi.xxsdk.request;

public class GetProxyStateRequest {
    /**
     * 代理服务器地址，ip:port
     */
    private String proxy;

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
