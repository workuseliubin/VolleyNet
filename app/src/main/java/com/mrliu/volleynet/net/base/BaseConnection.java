package com.mrliu.volleynet.net.base;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mrliu.volleynet.net.interfaces.BaseResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：liubin1 on 2016/8/31 16:00
 * 该类的说明：请求封装的父类，外部实现本类抽象,调用实现类的api
 * 修改历史：
 */
public abstract  class BaseConnection implements Response.Listener<BaseResponse>, Response.ErrorListener{

    private static final String TAG = "BaseConnection";
    private BaseResponseListener mBaseResponseListener;
    public  static String Token;
    private int requestType;
    private String jsonStr;
    private boolean showDialog;
    private BaseRequest request;


    public BaseConnection(){
        Log.i(TAG,"已经创建了一个新的请求...");
    }
    //抽象出两个方法
    public abstract String setURL();
    public abstract void parseResponse(BaseResponse response, String json);
    //post请求需要重写的方法
    public void postValue(Map<String, String> keyValue) {}

    //get方法
    public void get(boolean isShowDialog){
        setShowDialog(isShowDialog);
        request = new BaseRequest(Request.Method.GET,setURL(),this,this);
        request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 3, 1.0f));
        request.setShouldCache(false);
        request.setTag(this.getClass().getSimpleName());
        onStart();
        VolleyManager.INSTANCE.addQueue(request);
    }
    
    //post
    public void post(boolean isShowDialog){
        setShowDialog(isShowDialog);
        Map<String,String> mValue =new HashMap<>();
        postValue(mValue);
        request = new BaseRequest(Request.Method.POST,setURL(), mValue, this, this);
        //配置
        request.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 3, 1.0f));
        request.setShouldCache(false);
        request.setTag(this.getClass().getSimpleName());
        onStart();
        VolleyManager.INSTANCE.addQueue(request);
    }

    private void onStart() {
        BaseResponse response = new BaseResponse();
        response.setRequestType(requestType);
        response.setShowDialog(showDialog);
        if (mBaseResponseListener != null) mBaseResponseListener.onStart(response);
    }
    private void onSuccess(BaseResponse response) {
        response.setRequestType(requestType);
        response.setShowDialog(showDialog);
        if (mBaseResponseListener != null) mBaseResponseListener.onSuccess(response);
    }

    private void onFailure(BaseResponse response) {
        response.setRequestType(requestType);
        response.setShowDialog(showDialog);
        if (mBaseResponseListener != null) mBaseResponseListener.onFailure(response);
    }
    private void onStop() {
        BaseResponse response = new BaseResponse();
        response.setRequestType(requestType);
        response.setShowDialog(showDialog);
        if (mBaseResponseListener != null) mBaseResponseListener.onStop(response);
    }

    public void post() {
        this.post(true);
    }

    public void get() {
        this.get(false);
    }
    public void cancel() {
        if (request != null) {
            request.cancel();
            onStop();
        }
        VolleyManager.INSTANCE.stopRequest(this.getClass().getSimpleName());
    }
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        onStop();
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        onSuccess(baseResponse);
    }
    public BaseResponseListener getmBaseResponseListener() {
        return mBaseResponseListener;
    }

    public void setmBaseResponseListener(BaseResponseListener mBaseResponseListener) {
        this.mBaseResponseListener = mBaseResponseListener;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
            this.showDialog =true;
    }
}
