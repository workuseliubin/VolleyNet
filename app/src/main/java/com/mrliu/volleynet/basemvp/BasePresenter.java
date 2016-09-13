package com.mrliu.volleynet.basemvp;

import android.content.Context;

/**
 * 作者：liubin1 on 2016/9/13 10:52
 * 该类的说明：
 * 修改历史：
 */
public abstract class BasePresenter<M, V> {
    public M mModel;
    public V mView;


    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    protected abstract void onStart();

}

