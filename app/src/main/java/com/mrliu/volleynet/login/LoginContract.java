package com.mrliu.volleynet.login;

import android.widget.TextView;

import com.mrliu.volleynet.basemvp.BaseModel;
import com.mrliu.volleynet.basemvp.BasePresenter;
import com.mrliu.volleynet.basemvp.BaseView;
import com.mrliu.volleynet.net.interfaces.ModelCallBack;

/**
 * 作者：liubin1 on 2016/9/13 11:03
 * 该类的说明：
 * 修改历史：
 */
public interface LoginContract {
    interface Model extends BaseModel {
        void login(boolean isUsePassword,String username, String password, String authCode);
        void sendAuthCode(String username,TextView view);
        void setMoudelCallBack(ModelCallBack modelCallBack);
    }

    interface View extends BaseView {
        void showPassword();
        void showAuthCode();
        void showToast(String toasts);
        String getUserName();
        String getPassword();
        String getAuthCode();
        TextView getAuthCodeView();
    }

    abstract class Presenter extends BasePresenter<Model, View> {

    }
}
