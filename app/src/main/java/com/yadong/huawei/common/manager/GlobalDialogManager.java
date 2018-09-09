package com.yadong.huawei.common.manager;

import android.app.FragmentManager;

import com.yadong.huawei.module.widget.dialog.LoadingDialog;

/**
 * Dialog管理类
 */

public class GlobalDialogManager {

    private LoadingDialog mLoadingDialog;
    private boolean mIsShow;//是否显示

    private GlobalDialogManager() {
    }

    public static GlobalDialogManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static GlobalDialogManager INSTANCE = new GlobalDialogManager();
    }

    public void init() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
    }

    /**
     * 展示加载框
     *
     * @param manager
     */
    public synchronized void show(FragmentManager manager) {
        if (manager != null && mLoadingDialog != null && !mIsShow) {
            mLoadingDialog.showAllowingStateLoss(manager, "");
            mIsShow = true;
        }
    }

    /**
     * 隐藏加载框
     */
    public synchronized void dismiss() {
        if (mLoadingDialog != null && mIsShow) {
            mLoadingDialog.dismiss();
            mIsShow = false;
        }

    }
}
