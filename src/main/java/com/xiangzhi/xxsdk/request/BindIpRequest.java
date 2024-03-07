package com.xiangzhi.xxsdk.request;

public class BindIpRequest {
    /**
     * 静态代理服务器地址，仅静态代理产品有效
     */
    private String proxy;
    /**
     * 白名单列表中的序列号，不可超过当前可绑定的白名单数量上限。默认：1
     */
    private Integer i;

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }
}
