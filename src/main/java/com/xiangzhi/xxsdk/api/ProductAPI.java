package com.xiangzhi.xxsdk.api;

import com.aliyun.tea.TeaRequest;
import com.google.gson.reflect.TypeToken;
import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.APIResponse;
import com.xiangzhi.xxsdk.AppAuth;
import com.xiangzhi.xxsdk.bean.BindIpList;
import com.xiangzhi.xxsdk.bean.Proxy;
import com.xiangzhi.xxsdk.bean.ProxyState;
import com.xiangzhi.xxsdk.bean.Usage;
import com.xiangzhi.xxsdk.request.*;
import com.xiangzhi.xxsdk.utils.APIHelper;

import java.util.List;

public class ProductAPI {

    private final AppAuth appAuth;

    public ProductAPI(AppAuth appAuth) {
        this.appAuth = appAuth;
    }

    /**
     * 获取代理IP
     *
     * @param req
     * @return
     * @throws APIException
     */
    public List<Proxy> getProxies(GetProxyRequest req) throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/ip/get", req, appAuth);
        return APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<List<Proxy>>>(){});
    }

    /**
     * 查询剩余量
     *
     * @return
     * @throws APIException
     */
    public Usage getUsage() throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/ip/remaining", null, appAuth);
        return APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<Usage>>(){});
    }

    /**
     * 绑定白名单
     *
     * @param req
     * @throws APIException
     */
    public void bindIp(BindIpRequest req) throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/app/bindIp", req, appAuth);
        APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<String>>(){});
    }

    /**
     * 获取白名单列表
     *
     * @param req
     * @return
     * @throws APIException
     */
    public BindIpList getBindIpList(GetBindIpListRequest req) throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/app/getBindIpList", req, appAuth);
        return APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<BindIpList>>(){});
    }

    /**
     * 释放代理IP，仅长效代理产品有效
     *
     * @param req
     * @throws APIException
     */
    public void releaseProxy(ReleaseProxyRequest req) throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/ip/release", req, appAuth);
        APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<String>>(){});
    }

    /**
     * 查询使用中的代理IP，仅长效代理产品有效
     *
     * @return
     * @throws APIException
     */
    public List<ProxyState> getLockedProxies() throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/ip/lockedList", null, appAuth);
        return APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<List<ProxyState>>>(){});
    }

    /**
     * 查询使用中的代理IP，仅长效代理产品有效
     *
     * @return
     * @throws APIException
     */
    public ProxyState getProxyState(GetProxyStateRequest req) throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/ip/checkState", req, appAuth);
        ProxyState proxyState = APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<ProxyState>>() {});
        proxyState.setProxy(req.getProxy());
        return proxyState;
    }
}
