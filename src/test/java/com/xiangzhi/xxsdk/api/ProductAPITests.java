package com.xiangzhi.xxsdk.api;

import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.XiaoXiangSDKTestCase;
import com.xiangzhi.xxsdk.bean.BindIpList;
import com.xiangzhi.xxsdk.bean.Proxy;
import com.xiangzhi.xxsdk.bean.ProxyState;
import com.xiangzhi.xxsdk.request.GetProxyRequest;
import com.xiangzhi.xxsdk.request.GetProxyStateRequest;
import com.xiangzhi.xxsdk.request.ReleaseProxyRequest;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class ProductAPITests extends XiaoXiangSDKTestCase {

    @Test
    public void testGetProxies() throws APIException {
        ProductAPI api = new ProductAPI(getAuth(AppAuth.class));

        GetProxyRequest req = new GetProxyRequest();
        req.setCnt(1);
        List<Proxy> proxies = api.getProxies(req);
        System.out.println(proxies.size());

        System.out.println(api.getUsage().getRemaining());

        BindIpList bindIpList = api.getBindIpList(null);

        System.out.println(bindIpList.getLimit());
    }

    @Test
    public void testLongProxy() throws APIException {
        ProductAPI api = new ProductAPI(getAuth(AppAuth.class));

        List<ProxyState> lockedProxies = api.getLockedProxies();
        System.out.println(lockedProxies.size());

        GetProxyStateRequest getProxyStateRequest = new GetProxyStateRequest();
        getProxyStateRequest.setProxy("114.99.5.57:5021");
        ProxyState proxyState = api.getProxyState(getProxyStateRequest);
        System.out.println(proxyState.getState());

        ReleaseProxyRequest releaseProxyRequest = new ReleaseProxyRequest();
        releaseProxyRequest.setProxy("114.99.5.57:5021");
        api.releaseProxy(releaseProxyRequest);

        ProxyState proxyState2 = api.getProxyState(getProxyStateRequest);
        System.out.println(proxyState2.getState());
    }
}
