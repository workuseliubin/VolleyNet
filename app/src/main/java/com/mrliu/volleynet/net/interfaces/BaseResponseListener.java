package com.mrliu.volleynet.net.interfaces;

import com.mrliu.volleynet.net.base.BaseResponse;

/**
 * 作者：liubin1 on 2016/8/31 17:17
 * 该类的说明：
 * 修改历史：
 */
public interface BaseResponseListener {
    void onStart(BaseResponse br);
    void onStop(BaseResponse br);
    void onFailure(BaseResponse br);
    void onSuccess(BaseResponse br);
}
