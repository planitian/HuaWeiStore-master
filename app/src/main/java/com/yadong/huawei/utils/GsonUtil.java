package com.yadong.huawei.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

public class GsonUtil {

    private static Gson gson = new Gson();

    /**
     * 把json字符串转换为JavaBean
     */
    public static <T> T processJSON(String json, Class<T> beanClass) {
        T bean = null;
        try {
            bean = gson.fromJson(json, beanClass);
        } catch (Exception e) {
            Logger.e("解析json出现异常");
        }
        return bean;
    }


    /**
     * 把json字符串转换为JavaBean。如果json的根节点就是一个集合，则使用此方法
     * type参数的获取方式为：Type type = new TypeToken<集合泛型>(){}.getType();
     */
    public static <T> T json2Bean(String json, Type type) {
        T bean = null;
        try {
            bean = gson.fromJson(json, type);
        } catch (Exception e) {
            Logger.e("解析json出现异常");
        }
        return bean;
    }

    /**
     * 把JavaBean字符串转换为json
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
