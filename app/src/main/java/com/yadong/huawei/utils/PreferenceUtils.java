package com.yadong.huawei.utils;

/**
 * Sp工具类
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.yadong.huawei.common.app.App;


public class PreferenceUtils {

    private static SharedPreferences sp;

    private PreferenceUtils() {
    }

    private static Context context = App.getInstance();

    private static SharedPreferences initSharedPreference() {
        if (sp == null) {
            synchronized (PreferenceUtils.class) {
                if (sp == null) {
                    sp = context.getSharedPreferences(Constants.PREFERENCE_NAME,
                            Context.MODE_PRIVATE);
                }
            }
        }
        return sp;
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = initSharedPreference();
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key) {
        SharedPreferences sp = initSharedPreference();
        return sp.getBoolean(key, false);
    }

    public static void putString(String key, String value) {
        SharedPreferences sp = initSharedPreference();
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        SharedPreferences sp = initSharedPreference();
        return sp.getString(key, null);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = initSharedPreference();
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        SharedPreferences sp = initSharedPreference();
        return sp.getInt(key, -1);
    }

    public static void remove(String key) {
        if (key != null && !TextUtils.isEmpty(key)) {
            SharedPreferences sp = initSharedPreference();
            sp.edit().remove(key).commit();
        }
    }
}