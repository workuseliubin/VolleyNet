package com.mrliu.volleynet.login;

import com.mrliu.volleynet.net.interfaces.ModelCallBack;

/**
 * 作者：liubin1 on 2016/9/5 14:45
 * 该类的说明：
 * 修改历史：
 */
public class LoginPresenter extends LoginContract.Presenter implements ModelCallBack {


    public void login(boolean isUsePassword){
        mView.showDialog("正在登陆");
        mModel.login(isUsePassword,mView.getUserName(),mView.getPassword(),mView.getAuthCode());
    }

    public void sendAuthCode(){
        mModel.sendAuthCode(mView.getUserName(),mView.getAuthCodeView());
    }

    public void showPassword(){
        mView.showPassword();
    }

    public void showAuthCode(){
        mView.showAuthCode();
    }

    @Override
    public void onResult() {
        mView.hideDialog();
    }

    @Override
    protected void onStart() {
        mModel.setMoudelCallBack(this);
    }
}
