package com.mrliu.volleynet.net.request;

import com.mrliu.volleynet.login.LoginBean;
import com.mrliu.volleynet.net.base.BaseConnection;
import com.mrliu.volleynet.net.base.BaseResponse;
import com.mrliu.volleynet.net.base.RequestUrlUtil;
import com.mrliu.volleynet.utils.JsonUtils;

import java.util.Map;

/**
 * 作者：liubin1 on 2016/9/2 14:03
 * 该类的说明：
 * 修改历史：
 */
public class LoginRequest extends BaseConnection {

    public  static String Token;
    public String mobile;
    public String code;

    public LoginRequest(String mobile, String code, String password) {
        this.mobile = mobile;
        this.code = code;
        this.password = password;
    }

    public String password;

    @Override
    public String setURL() {
        return new RequestUrlUtil.Builder()
                .setHost()
                .setPath("/api/login")
                .build();
    }

    @Override
    public void parseResponse(BaseResponse response, String json) {
        LoginBean loginBean = JsonUtils.toObject(json,LoginBean.class);
        response.setData(loginBean);
    }

    @Override
    public void postValue(Map<String, String> keyValue) {
        keyValue.put("mobile",mobile);
        keyValue.put("password",password);
        keyValue.put("code",code);
        keyValue.put("source","3");
        keyValue.put("registrationId","119");
    }
}
