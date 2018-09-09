package com.yadong.huawei.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义的toast工具类，只展示一个toast，不重复展示
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, CharSequence text) {
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(Context mContext, CharSequence text) {
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}