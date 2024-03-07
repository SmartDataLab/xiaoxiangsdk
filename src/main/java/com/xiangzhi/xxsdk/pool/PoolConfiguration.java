package com.xiangzhi.xxsdk.pool;

public class PoolConfiguration {
    /**
     * 提取ip接口的调用间隔（秒），默认10秒。
     */
    private Integer fetchApiInterval = 10;

    /**
     * 每次提取ip数量，默认1。
     */
    private Integer fetchNumOnce = 1;

    /**
     * 代理ip的存活时长（秒），默认60秒。
     * 从提取入池开始计时，超过存活时长的代理ip将不再被使用。
     */
    private Integer proxyLiveTime = 60;

    /**
     * 代理ip最大使用次数，默认不限。
     * 超过使用次数的代理ip将不再被使用。
     */
    private Integer proxyMaxUsed = null;
    /**
     * 代理ip选择策略，默认轮询。
     */
    private SelectionStrategy strategy = SelectionStrategy.Round;

    public Integer getFetchApiInterval() {
        return fetchApiInterval;
    }

    public void setFetchApiInterval(Integer fetchApiInterval) {
        this.fetchApiInterval = fetchApiInterval;
    }

    public Integer getFetchNumOnce() {
        return fetchNumOnce;
    }

    public void setFetchNumOnce(Integer fetchNumOnce) {
        this.fetchNumOnce = fetchNumOnce;
    }

    public Integer getProxyLiveTime() {
        return proxyLiveTime;
    }

    public void setProxyLiveTime(Integer proxyLiveTime) {
        this.proxyLiveTime = proxyLiveTime;
    }

    public Integer getProxyMaxUsed() {
        return proxyMaxUsed;
    }

    public void setProxyMaxUsed(Integer proxyMaxUsed) {
        this.proxyMaxUsed = proxyMaxUsed;
    }

    public SelectionStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public enum SelectionStrategy {
        // 轮询
        Round,
        // 随机
        RANDOM
    }
}
