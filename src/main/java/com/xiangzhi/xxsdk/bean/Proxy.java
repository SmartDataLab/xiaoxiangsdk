package com.xiangzhi.xxsdk.bean;


import java.util.Date;
import java.util.Objects;

public class Proxy {
    /**
     * 代理服务器IP
     */
    private String ip;
    /**
     * 代理服务器端口
     */
    private Integer port;
    /**
     * 代理服务器真实出口IP（返回空时，出口IP即代理服务器IP）
     */
    private String realIp;
    /**
     * 代理IP上线时间
     */
    private Date startTime;
    /**
     * 代理IP存活时长（分钟）
     */
    private Integer during;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDuring() {
        return during;
    }

    public void setDuring(Integer during) {
        this.during = during;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proxy p = (Proxy) o;
        return ip.equals(p.ip) && port.equals(p.port);
    }
}
