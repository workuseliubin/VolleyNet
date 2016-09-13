package com.mrliu.volleynet.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mrliu.volleynet.R;
import com.mrliu.volleynet.basemvp.BaseModel;
import com.mrliu.volleynet.basemvp.BasePresenter;
import com.mrliu.volleynet.basemvp.BaseView;
import com.mrliu.volleynet.utils.StringUtils;
import com.mrliu.volleynet.utils.TypeUtil;
import com.mrliu.volleynet.view.IToast;
import com.mrliu.volleynet.view.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 作者：liubin1 on 2016/9/5 17:43
 * 该类的说明：
 * 修改历史：
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView{

    public LoadingDialog loadingDialog;
    public Context mContext ;
    public T mPresenter;
    public E mModel;

    protected abstract int setLayoutId() ;
    protected abstract void initView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(this.setLayoutId());
        ButterKnife.bind(this);
        mPresenter = TypeUtil.getT(this, 0);
        mModel = TypeUtil.getT(this, 1);
        initView();
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
    }

    /**
     * 弹出正在加载...对话框。
     */
    protected void showLoadingDialog(String text) {
        if (loadingDialog == null || loadingDialog.getContext() != mContext) {
            loadingDialog = new LoadingDialog(mContext);
        }
        loadingDialog.show(text);
    }

    protected void dismissLoadingDialog() {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
        }
    }

    protected void Toast(final String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            return;
        }
        runOnUiThread(()-> IToast.getIToast().setImage(R.mipmap.toast_warn).show(text));
    }

    @Override
    public void showDialog(String dialogs) {
        showLoadingDialog(dialogs);
    }

    @Override
    public void hideDialog() {
        dismissLoadingDialog();
    }
}
