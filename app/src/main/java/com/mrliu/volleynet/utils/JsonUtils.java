package com.mrliu.volleynet.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 作者：liubin1 on 2016/9/2 15:53
 * 该类的说明：
 * 修改历史：
 */
public class JsonUtils {

    private static Gson gson =new Gson();

    public static String toString(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> toList(String json, Type type) {
        return (ArrayList<T>) gson.fromJson(json, type);
    }
}
