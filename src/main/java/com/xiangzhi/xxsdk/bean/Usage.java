package com.xiangzhi.xxsdk.bean;

public class Usage {
    /**
     * 应用账号
     */
    private String appKey;
    /**
     * 总量
     */
    private Long total;
    /**
     * 剩余量
     */
    private Long remaining;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }
}
