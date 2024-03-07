package com.xiangzhi.xxsdk.api;

import com.aliyun.tea.TeaRequest;
import com.google.gson.reflect.TypeToken;
import com.xiangzhi.xxsdk.APIException;
import com.xiangzhi.xxsdk.APIResponse;
import com.xiangzhi.xxsdk.UserAuth;
import com.xiangzhi.xxsdk.bean.Wallet;
import com.xiangzhi.xxsdk.utils.APIHelper;

public class AccountAPI {

    private final UserAuth userAuth;

    public AccountAPI(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public Long getBalance() throws APIException {
        TeaRequest teaRequest = APIHelper.buildTeaRequest("/account/getBalance", null, userAuth);
        Wallet wallet = APIHelper.doRequest(teaRequest, new TypeToken<APIResponse<Wallet>>() {});
        return wallet.getBalance();
    }
}
