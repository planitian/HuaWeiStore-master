package com.yadong.huawei.utils;

import com.orhanobut.logger.Logger;

/**
 * Log工具类
 */
public class LogUtil {

    public static boolean isDebug = true;

    public static void e(String msg) {
        if (isDebug) {
            Logger.e(msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.w(msg);
        }
    }


    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg);
        }
    }
}
