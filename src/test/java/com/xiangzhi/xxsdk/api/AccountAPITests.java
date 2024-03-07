package com.xiangzhi.xxsdk.api;

import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.UserAuth;
import com.xiangzhi.xxsdk.XiaoXiangSDKTestCase;

public class AccountAPITests extends XiaoXiangSDKTestCase {

    public void testGetBalance() throws APIException {
        AccountAPI api = new AccountAPI(getAuth(UserAuth.class));
        Long balance = api.getBalance();
        System.out.println(balance);
    }
}
