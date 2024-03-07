package com.xiangzhi.xxsdk.request;

public class GetBindIpListRequest {
    /**
     * 静态代理服务器地址，仅静态代理产品有效
     */
    private String proxy;

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
