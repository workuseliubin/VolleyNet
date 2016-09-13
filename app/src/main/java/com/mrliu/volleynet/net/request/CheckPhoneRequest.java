package com.mrliu.volleynet.net.request;

import com.mrliu.volleynet.net.base.BaseConnection;
import com.mrliu.volleynet.net.base.BaseResponse;
import com.mrliu.volleynet.net.base.RequestUrlUtil;

import java.util.Map;

/**
 * 作者：liubin1 on 2016/9/6 14:41
 * 该类的说明：
 * 修改历史：
 */
public class CheckPhoneRequest extends BaseConnection {
    private String userName;

    public CheckPhoneRequest(String userName) {
        this.userName = userName;
    }

    @Override
    public String setURL() {
        return new RequestUrlUtil.Builder()
                .setHost()
                .setPath("/api/login/checkExists")
                .build();
    }

    @Override
    public void parseResponse(BaseResponse response, String json) {

    }

    @Override
    public void postValue(Map<String, String> keyValue) {
        keyValue.put("mobile",userName);
    }
}
