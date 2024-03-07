package com.xiangzhi.xxsdk.pool;

import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.Auth;
import com.xiangzhi.xxsdk.XiaoXiangSDKTestCase;
import com.xiangzhi.xxsdk.bean.Proxy;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyPoolTests extends XiaoXiangSDKTestCase {
    private static final Logger logger = LoggerFactory.getLogger(ProxyPoolTests.class);

    public void testPool() throws InterruptedException, APIException {
        AppAuth appAuth = getAuth(AppAuth.class);

        PoolConfiguration configuration = new PoolConfiguration();
        configuration.setFetchNumOnce(1);
        configuration.setStrategy(PoolConfiguration.SelectionStrategy.Round);
//        configuration.setProxyMaxUsed(3);
        ProxyPool pool = new ProxyPool(appAuth, configuration);
        pool.startApiWork();

        for (int i = 0; i < 20; i++) {
            Proxy p = pool.get();
            if (p == null) {
                logger.debug("get proxy: null");
            } else {
                logger.debug("get proxy: {}:{}", p.getIp(), p.getPort());
            }
            Thread.sleep(2000);
        }

        pool.stopApiWork();
    }

}
