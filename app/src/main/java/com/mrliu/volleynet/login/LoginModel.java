package com.mrliu.volleynet.login;

import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;

import com.mrliu.volleynet.contants.RequestType;
import com.mrliu.volleynet.net.base.BaseConnection;
import com.mrliu.volleynet.net.base.BaseResponse;
import com.mrliu.volleynet.net.interfaces.BaseResponseListener;
import com.mrliu.volleynet.net.interfaces.ModelCallBack;
import com.mrliu.volleynet.net.request.AuthCodeRequest;
import com.mrliu.volleynet.net.request.CheckPhoneRequest;
import com.mrliu.volleynet.net.request.LoginRequest;
import com.mrliu.volleynet.utils.StringUtils;
import com.mrliu.volleynet.view.IToast;

/**
 * 作者：liubin1 on 2016/9/5 14:40
 * 该类的说明：
 * 修改历史：
 */
public class LoginModel implements LoginContract.Model , BaseResponseListener {
   public IToast iToast =new IToast();
    private long tempTime = 59;
    private TextView view;

    private ModelCallBack modelCallBack;
    private String userName;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (tempTime == 1) {
                view.setEnabled(true);
                view.setText("验证码");
                view.setTextColor(Color.parseColor("#71e2dc"));
                handler.removeCallbacksAndMessages(null);
            } else {
                String timeStr = "验证码(" + (tempTime -= 1) + ")";
                view.setTextColor(Color.parseColor("#dadadb"));
                view.setText(timeStr);
                sendEmptyMessageDelayed(0, 1000L);
            }
        }
    };
    @Override
    public void login(boolean isUsePassword,String username, String password, String authCode) {

        if (StringUtils.isEmpty(username) && StringUtils.validatePhoneNumber(username)) {
            iToast.show("手机号不存在");
            return;
        }

        if (!isUsePassword) {
            if (StringUtils.isEmpty(authCode)) {
                iToast.show("验证码输入为空");
                return;
            }
            //验证
            login(username, null, authCode);
        } else {
            if (StringUtils.isEmpty(password)) {
                iToast.show("密码输入为空");
                return;
            }
            if (!StringUtils.isOkPassword(password)) {
                iToast.show("请输入正确的密码");
                return;
            }
            login(username, password, null);
        }
    }
    @Override
    public void sendAuthCode(String username, TextView view) {
        this.view =view;
        this.userName = username;
        CheckPhoneRequest checkPhoneRequest = new CheckPhoneRequest(username);
        checkPhoneRequest.setRequestType(RequestType.REQUESTCHECKPHONE);
        checkPhoneRequest.post();
        checkPhoneRequest.setmBaseResponseListener(this);

    }

    @Override
    public void setMoudelCallBack(ModelCallBack modelCallBack) {
        this.modelCallBack =modelCallBack;
    }

    @Override
    public void onStart(BaseResponse br) {

    }

    @Override
    public void onStop(BaseResponse br) {
        modelCallBack.onResult();
    }

    @Override
    public void onFailure(BaseResponse br) {
        modelCallBack.onResult();
    }

    @Override
    public void onSuccess(BaseResponse br) {
        modelCallBack.onResult();
        if(RequestType.REQUESTLOGIN==br.getRequestType()){
            if(0 == br.getErrno()){
                LoginBean loginBean= (LoginBean) br.getData();
                if(null != loginBean){
                    BaseConnection.Token = loginBean.token;
                    iToast.show("登陆成功");
                }else{
                    iToast.show("登录失败");
                }
            }
        }else if(RequestType.REQUESTAUTHCODE==br.getRequestType()){
            if(0 == br.getErrno()){
            }else{
                if (!StringUtils.isNullOrEmpty(br.getMsg())) {
                    iToast.show(br.getMsg());
                } else {
                    iToast.show("获取验证码异常");
                }
            }
        }else if(RequestType.REQUESTCHECKPHONE==br.getRequestType()){
            int errno = br.getErrno();
            if(0 == errno){
                iToast.show("该手机号未注册");
            }else if(2001 == errno){
                tempTime = 59;
                handler.sendEmptyMessageDelayed(0, 0);
                view.setEnabled(false);
                AuthCodeRequest authCodeRequest = new AuthCodeRequest(userName);
                authCodeRequest.setRequestType(RequestType.REQUESTAUTHCODE);
                authCodeRequest.post();
                authCodeRequest.setmBaseResponseListener(this);
            }
        }
    }

    private void login(final String name, final String password, final String code) {
        LoginRequest loginRequest = new LoginRequest(name, code, password);
        loginRequest.setRequestType(RequestType.REQUESTLOGIN);
        loginRequest.post();
        loginRequest.setmBaseResponseListener(this);
    }
}
