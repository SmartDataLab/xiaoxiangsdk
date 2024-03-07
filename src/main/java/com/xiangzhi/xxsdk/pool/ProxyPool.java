package com.xiangzhi.xxsdk.pool;

import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.api.ProductAPI;
import com.xiangzhi.xxsdk.bean.Proxy;
import com.xiangzhi.xxsdk.request.GetProxyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProxyPool {
    private static final Logger logger = LoggerFactory.getLogger(ProxyPool.class);

    private final PoolConfiguration configuration;

    private ProductAPI api;

    private ScheduledExecutorService apiExecutor = null;

    private LinkedList<Proxy> proxyList = new LinkedList<>();
    private Map<Proxy, ProxyMeter> meters = new HashMap<>();

    public ProxyPool(AppAuth appAuth, PoolConfiguration configuration) {
        this.configuration = configuration;

        this.api = new ProductAPI(appAuth);
    }

    public synchronized void startApiWork() {
        if (apiExecutor == null || apiExecutor.isTerminated()) {
            apiExecutor = Executors.newScheduledThreadPool(1);
            apiExecutor.scheduleAtFixedRate(() -> fillProxyList(), 0, configuration.getFetchApiInterval(), TimeUnit.SECONDS);
        }
    }

    public synchronized void stopApiWork() throws InterruptedException {
        if (apiExecutor != null && !apiExecutor.isShutdown()) {
            apiExecutor.shutdown();
            while (apiExecutor.awaitTermination(3, TimeUnit.SECONDS)) {
                break;
            }
        }
    }

    public synchronized Proxy get() {
        if (proxyList.size() == 0) {
            return null;
        }
        PoolConfiguration.SelectionStrategy strategy = configuration.getStrategy();
        Integer maxUsed = configuration.getProxyMaxUsed();
        Integer liveTime = configuration.getProxyLiveTime();

        while (proxyList.size() > 0) {
            Proxy p;
            if (strategy == PoolConfiguration.SelectionStrategy.Round) {
                p = proxyList.poll();
            } else {
                p = proxyList.get(new Random().nextInt(proxyList.size()));
            }
            ProxyMeter m = meters.get(p);

            boolean removing = false;
            if (System.currentTimeMillis() > p.getStartTime().getTime() + p.getDuring() * 60000) {
                logger.debug("removing expired proxy. {}:{}", p.getIp(), p.getPort());
                removing = true;
            } else if (maxUsed != null && maxUsed > 0 && m.useIncr() > maxUsed) {
                logger.debug("removing overMaxUsed proxy. {}:{}", p.getIp(), p.getPort());
                removing = true;
            } else if (liveTime != null && liveTime > 0 && System.currentTimeMillis() - m.getActTime() > liveTime * 1000) {
                logger.debug("removing overLifeTime proxy. {}:{}", p.getIp(), p.getPort());
                removing = true;
            }
            if (removing) {
                if (strategy == PoolConfiguration.SelectionStrategy.RANDOM) {
                    proxyList.remove(p);
                }
                meters.remove(p);
            } else {
                if (strategy == PoolConfiguration.SelectionStrategy.Round) {
                    proxyList.addLast(p);
                }
                return p;
            }
        }


        return null;
    }

    public synchronized void remove(Proxy p) {
        proxyList.remove(p);
        meters.remove(p);
    }

    private void fillProxyList() {
        GetProxyRequest request = new GetProxyRequest();
        request.setCnt(configuration.getFetchNumOnce());
        try {
            List<Proxy> proxyList = api.getProxies(request);

            synchronized (this) {
                for (Proxy p : proxyList) {
                    ProxyMeter m = meters.get(p);
                    if (m != null) {
                        Proxy old = m.getProxy();
                        //重复提取
                        if (p.getStartTime().equals(old.getStartTime())) {
                            logger.debug("duplicated proxy ignored. {}:{}", p.getIp(), p.getPort());
                            continue;
                        } else {
                            this.proxyList.remove(old);
                            meters.remove(old);
                        }
                    }

                    this.proxyList.addLast(p);
                    meters.put(p, new ProxyMeter(p));
                }
                logger.debug("proxy pool filled. curSize:{}", this.proxyList.size());
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
