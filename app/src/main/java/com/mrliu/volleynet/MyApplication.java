package com.mrliu.volleynet;

import android.app.Application;
import android.content.Context;

import com.mrliu.volleynet.net.base.VolleyManager;

/**
 * 作者：liubin1 on 2016/9/1 15:15
 * 该类的说明：
 * 修改历史：
 */
public class MyApplication extends Application{

    public static Context mAPPContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAPPContext=getApplicationContext();
        VolleyManager.INSTANCE.initQueue();
    }
}
