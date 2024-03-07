package com.xiangzhi.xxsdk.bean;

import java.util.Date;

public class ProxyState {
    /**
     * 代理服务器地址，ip:port
     */
    private String proxy;
    /**
     * 当前状态：1 使用中，2 已释放
     */
    private Integer state;
    /**
     * 开始使用时间
     */
    private Date lockTime;
    /**
     * 释放时间
     */
    private Date releaseTime;
    /**
     * 释放类型：0 主动释放，1 服务器自动释放
     */
    private Integer releaseType;

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }
}
