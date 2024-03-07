import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.bean.Proxy;
import com.xiangzhi.xxsdk.pool.PoolConfiguration;
import com.xiangzhi.xxsdk.pool.ProxyPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代理ip池（ProxyPool）使用示例
 *
 * ProxyPool作为sdk的高级玩法，为用户提供极简的调用方式来获取代理IP，以及维护代理IP在本地池中的生存周期。
 *
 * ProxyPool具体功能描述如下：
 *  首先，ProxyPool定时自动的调用提取IP接口，将接口返回的代理IP放入本地池中。
 *  用户程序中在需要使用代理IP的时候，从本地池中以轮询或随机算法获取代理IP。
 *  当代理IP满足过期失效条件，本地池自动淘汰该代理IP，不会再被用户程序获取。
 */
public class UseProxyPool {
    private static final Logger logger = LoggerFactory.getLogger(UseProxyPool.class);

    public static void main(String[] args) throws InterruptedException {

        AppAuth appAuth = new AppAuth("key", "secret");

        PoolConfiguration configuration = new PoolConfiguration();
        //设置调用提取接口每次提取的ip数量
        configuration.setFetchNumOnce(1);
        //设置获取代理IP的算法策略为：轮询
        configuration.setStrategy(PoolConfiguration.SelectionStrategy.Round);
        //设置代理IP失效触发条件：被使用3次后失效
        configuration.setProxyMaxUsed(3);
        //设置代理IP失效触发条件：从提取入池开始计时30秒后失效
//        configuration.setProxyLiveTime(30);

        ProxyPool pool = new ProxyPool(appAuth, configuration);
        //启动自动调用提取接口的线程
        pool.startApiWork();

        for (int i = 0; i < 10; i++) {
            //从本地池中获取代理ip
            Proxy p = pool.get();
            //有可能池中还没有代理ip，返回的是null需要处理。
            if (p == null) {
                logger.debug("get proxy: null");
            } else {
                logger.debug("get proxy: {}:{}", p.getIp(), p.getPort());
            }
            Thread.sleep(2000);
        }

        //停止自动调用提取接口的线程
        pool.stopApiWork();
    }
}

