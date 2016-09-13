package com.mrliu.volleynet.utils;

import java.lang.reflect.ParameterizedType;

/**
 * 作者：liubin1 on 2016/9/13 11:15
 * 该类的说明：
 * 修改历史：
 */
public class TypeUtil {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
