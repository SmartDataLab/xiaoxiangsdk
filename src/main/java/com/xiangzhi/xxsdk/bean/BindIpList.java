package com.xiangzhi.xxsdk.bean;

public class BindIpList {
    /**
     * 白名单最大数量
     */
    private Integer limit;
    /**
     * 已经绑定的白名单数量
     */
    private Integer count;
    /**
     * 白名单IP 列表
     */
    private String[] bindIpsList;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String[] getBindIpsList() {
        return bindIpsList;
    }

    public void setBindIpsList(String[] bindIpsList) {
        this.bindIpsList = bindIpsList;
    }
}
