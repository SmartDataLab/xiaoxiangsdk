package com.xiangzhi.xxsdk.utils;

import com.aliyun.tea.Tea;
import com.aliyun.tea.TeaRequest;
import com.aliyun.tea.TeaResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiangzhi.xxsdk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class APIHelper {
    private static final Logger logger = LoggerFactory.getLogger(APIHelper.class);

    private static final String API_HOST = "api.xiaoxiangdaili.com";

    public static <R> TeaRequest buildTeaRequest(String path, R bizRequest, Auth auth) {
        TeaRequest request = TeaRequest.create();
        request.protocol = "https";
        request.headers.put("host", API_HOST);
        request.pathname = path;

        if (auth instanceof AppAuth) {
            request.query.put("appKey", auth.key());
            request.query.put("appSecret", auth.secret());
        } else if (auth instanceof UserAuth) {
            request.query.put("userKey", auth.key());
            request.query.put("userSecret", auth.secret());
        }

        if (bizRequest != null) {
            for (Field f : bizRequest.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Object val = f.get(bizRequest);
                    if (val != null) {
                        request.query.put(f.getName(), String.valueOf(val));
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }

        return request;
    }

    public static <T> T doRequest(TeaRequest teaRequest, TypeToken<APIResponse<T>> typeToken) throws APIException {
        try {
            TeaResponse teaResponse = Tea.doAction(teaRequest, APIHelper.getRuntimeOptions());
            if (teaResponse.statusCode == 200) {
                String resp = teaResponse.getResponseBody();
                logger.debug("resp: {}", resp);

                APIResponse<T> o = new Gson().fromJson(resp, typeToken.getType());
                if (!o.getSuccess()) {
                    throw new APIException("接口返回错误！code=" + o.getCode() + ",msg=" + o.getMsg());
                }
                return o.getData();
            }
        } catch (Exception e) {
            if (e instanceof APIException) {
                throw (APIException) e;
            } else {
                throw new APIException("未知异常", e);
            }
        }

        return null;
    }

    public static Map<String, Object> getRuntimeOptions() {
        return Collections.EMPTY_MAP;
    }
}
