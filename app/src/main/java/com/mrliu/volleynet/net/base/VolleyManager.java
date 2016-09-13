package com.mrliu.volleynet.net.base;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mrliu.volleynet.MyApplication;

/**
 * 作者：liubin1 on 2016/9/1 14:35
 * 该类的说明：Volley队列的管理类
 * 修改历史：
 */
public enum VolleyManager {
    INSTANCE;
    private RequestQueue mRequestQueue=null;

    public void initQueue(){
        if(null == mRequestQueue){
            mRequestQueue= Volley.newRequestQueue(MyApplication.mAPPContext);
        }
    }

    public void addQueue(Request request){
        mRequestQueue.add(request);
    }

    public void stopRequest(Object tag) {
        if (tag != null)
            mRequestQueue.cancelAll(tag);
    }
}
