package com.mrliu.volleynet.net.base;

import java.util.ArrayList;

/**
 * 作者：liubin1 on 2016/8/31 11:15
 * 该类的说明：
 * 修改历史：
 */
public class BaseResponse {

    //请求码
    private int result;
    //错误码
    private int errno;
    //请求类型，用于单activity多个请求的区分
    private int requestType;
    //信息
    private String msg;
    //请求回来的JSON字符串
    private String jsonStr;
    //错误信息
    private String errorMsgs;
    //待用，可以存放解析后的JSON Array
    private ArrayList<Object> datas=new ArrayList<>();
    //存放解析后的数据
    private Object data;
    //是否展示dialog
    private boolean showDialog;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(String errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public ArrayList<Object> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Object> datas) {
        this.datas = datas;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

}
