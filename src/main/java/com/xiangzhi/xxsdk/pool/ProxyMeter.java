package com.xiangzhi.xxsdk.pool;


import com.xiangzhi.xxsdk.bean.Proxy;

import java.util.concurrent.atomic.AtomicInteger;

public class ProxyMeter {
    private Proxy proxy;
    private Long actTime;
    private AtomicInteger usedTimes = new AtomicInteger(0);


    public ProxyMeter(Proxy proxy) {
        this.proxy = proxy;
        this.actTime = System.currentTimeMillis();
    }

    public int useIncr() {
        return usedTimes.incrementAndGet();
    }

    public Proxy getProxy() {
        return proxy;
    }

    public Long getActTime() {
        return actTime;
    }

    public Integer getUsedTimes() {
        return usedTimes.get();
    }
}
