package com.mrliu.volleynet.view;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mrliu.volleynet.MyApplication;
import com.mrliu.volleynet.R;

/**
 * 作者：liubin1
 * 该类的说明：Toast
 * 修改历史：
 */
public class IToast {
    private static IToast iToast;
    private Toast toast;
    private ImageView iv_img;
    private TextView tv;

    public IToast() {
        View v = LayoutInflater.from(MyApplication.mAPPContext).inflate(R.layout.toast, null);
        iv_img = (ImageView) v.findViewById(R.id.iv_img);
        tv = (TextView) v.findViewById(R.id.tv_toast);
        toast = new Toast(MyApplication.mAPPContext);
        toast.setView(v);
    }

    public static IToast getIToast() {
        if (null == iToast) {
            iToast = new IToast();
        }
        return iToast;
    }

    public IToast setImage(int resId) {
        if (null == iToast) {
            iToast = new IToast();
        }
        iv_img.setImageResource(resId);
        return iToast;
    }

    public void show(String text) {
        tv.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void show(int resId) {
        tv.setText(resId);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void cancleToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
