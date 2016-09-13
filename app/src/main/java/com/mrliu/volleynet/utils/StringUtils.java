package com.mrliu.volleynet.utils;

import android.content.Context;
import android.widget.EditText;

import com.mrliu.volleynet.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理字符串工具类
 */
public class StringUtils {

    /**
     * 判断是否为空
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || "".equals(text.trim()) || text.trim().length() == 0
                || "null".equals(text.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串数组texts中是否有一个字符串为空
     *
     * @param texts
     * @return 如果字符串数组texts中有一个为空或texts为空，返回true;otherwise return false;
     */
    public static boolean isEmpty(String... texts) {
        if (texts == null || texts.length == 0) {
            return true;
        }
        for (String text : texts) {
            if (text == null || "".equals(text.trim()) || text.trim().length() == 0
                    || "null".equals(text.trim())) {
                return true;
            }
        }
        return false;
    }



    /**
     * @param password 注册的密码
     * @return 是否合法
     */
    public static boolean isOkPassword(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^(?!^[0-9]+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,20}$");
        Matcher m = pattern.matcher(password);
        return m.matches();
    }


    /**
     * 电话号码验证
     *
     * @param phoneNumber 手机号码
     * @return
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isNullOrEmpty(phoneNumber)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = pattern.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MyApplication.mAPPContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取Editext的输入内容
     *
     * @param et
     * @return
     */
    public static String getText(EditText et) {
        if (et != null) {
            return et.getText().toString().trim();
        }
        return "";
    }
}
