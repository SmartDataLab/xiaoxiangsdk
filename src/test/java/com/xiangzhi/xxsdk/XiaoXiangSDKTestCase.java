package com.xiangzhi.xxsdk;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;

public class XiaoXiangSDKTestCase extends TestCase {

    public <T extends Auth> T getAuth(Class<T> tClass) throws APIException {
        return getAuth(tClass, null);
    }

    public <T extends Auth> T getAuth(Class<T> tClass, String keyName) throws APIException {
        try {
            String pk = System.getProperty("authKey" + (keyName == null ? "": ("." + keyName)));
            String key = pk != null ? pk : "key";
            String ps = System.getProperty("authSecret" + (keyName == null ? "": ("." + keyName)));
            String secret = ps != null ? ps : "secret";
            Constructor<T> constructor = tClass.getConstructor(String.class, String.class);
            return constructor.newInstance(key, secret);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }
}
