package com.mrliu.volleynet.view;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliu.volleynet.R;

/**
 * 作者：liubin1
 * 该类的说明：LoadingDialog
 * 修改历史：
 */
public class LoadingDialog {
    private static Context mContext;
    private static Dialog dialog;
    private static ImageView iv_loading;
    private static TextView tv;

    public LoadingDialog(Context context) {
        if (null != context && mContext != context) {
            mContext = context;
            View v = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            iv_loading = (ImageView) v.findViewById(R.id.iv_loading);
            tv = (TextView) v.findViewById(R.id.tv_loading);
            dialog = new Dialog(context, R.style.loading_dialog);
            dialog.setContentView(v);
        }
    }

    public Context getContext() {
        return mContext;
    }

    public void show() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_loading, "rotation", 0F, -360F);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(100);
        objectAnimator.setRepeatMode(ObjectAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        dialog.show();
    }

    public void show(String loading) {
        tv.setText(loading);
        show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
