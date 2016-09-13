package com.mrliu.volleynet.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrliu.volleynet.R;
import com.mrliu.volleynet.login.LoginContract;
import com.mrliu.volleynet.login.LoginModel;
import com.mrliu.volleynet.login.LoginPresenter;
import com.mrliu.volleynet.utils.StringUtils;
import com.mrliu.volleynet.utils.SystemUtils;
import com.mrliu.volleynet.view.DeleteEditText;

import butterknife.Bind;

public class LoginActivity extends BaseActivity< LoginPresenter , LoginModel> implements View.OnLayoutChangeListener, View.OnClickListener, LoginContract.View {
    @Bind(R.id.et_phone)
    DeleteEditText et_phone;//输入输入手机号
    @Bind(R.id.ll_phone)
    LinearLayout ll_phone;//验证码登陆
    @Bind(R.id.et_authcode)
    EditText et_authcode;//输入验证码
    @Bind(R.id.tv_authcode)
    TextView tv_authcode;//验证码
    @Bind(R.id.ll_password)
    LinearLayout ll_password;//输入密码
    @Bind(R.id.et_passwrod)
    EditText et_passwrod;//输入密码
    @Bind(R.id.tv_forget_passwrod)
    TextView tv_forget_passwrod;//忘记密码
    @Bind(R.id.login)
    ImageButton login;//登陆
    @Bind(R.id.tv_use_passwrod)
    TextView tv_use_passwrod;//使用密码
    @Bind(R.id.ll_main)
    LinearLayout ll_main;
    @Bind(R.id.ll_bottom)
    LinearLayout ll_bottom;
    private String TAG = "login";
    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0;//软件盘弹起后所占高度阀值
    private boolean isUsePassword = true;
    private String password;
    private String name;

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        ll_main.addOnLayoutChangeListener(this);
    }

    private void initClick() {
        tv_use_passwrod.setOnClickListener(this);
        login.setOnClickListener(this);
        tv_authcode.setOnClickListener(this);
        tv_forget_passwrod.setOnClickListener(this);
        ll_main.setOnClickListener(this);
    }

    private void initInput() {
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            ll_bottom.setVisibility(View.GONE);
            String s = et_phone.getText().toString();
            et_phone.setText(s + "");
            et_phone.setSelection(et_phone.getText().length());
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            ll_bottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.login_ac;
    }

    @Override
    protected void initView() {
        initClick();
        initInput();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_use_passwrod:
                //使用密码
                mPresenter.showPassword();
                break;
            case R.id.tv_authcode:
                SystemUtils.hideSoftKeyBoard(LoginActivity.this);
                mPresenter.sendAuthCode();
                break;
            case R.id.tv_forget_passwrod:
                //点击忘记密码
                mPresenter.showAuthCode();
                break;
            case R.id.login:
                mPresenter.login(isUsePassword);
                break;
            case R.id.ll_main:
                SystemUtils.hideSoftKeyBoard(LoginActivity.this);
                break;
        }
    }

    @Override
    public void showPassword() {
        ll_phone.setVisibility(View.INVISIBLE);
        ll_password.setVisibility(View.VISIBLE);
        tv_use_passwrod.setVisibility(View.INVISIBLE);
        isUsePassword = true;
    }

    @Override
    public void showAuthCode() {
        ll_password.setVisibility(View.INVISIBLE);
        ll_phone.setVisibility(View.VISIBLE);
        tv_use_passwrod.setVisibility(View.VISIBLE);
        isUsePassword = false;
    }

    @Override
    public String getUserName() {
        name = StringUtils.getText(et_phone);
        return name;
    }

    @Override
    public String getPassword() {
        password = StringUtils.getText(et_passwrod);
        return password;
    }

    @Override
    public String getAuthCode() {
        return StringUtils.getText(et_authcode);
    }

    @Override
    public void showToast(String toasts) {
        Toast.makeText(this, toasts, Toast.LENGTH_SHORT).show();
    }

    @Override
    public TextView getAuthCodeView() {
        return tv_authcode;
    }
}
