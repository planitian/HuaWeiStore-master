package com.yadong.huawei.module.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.AppActivityManager;

/**
 * 基类Activity
 * 当前基类Activity中并没有提供mPresenter这个字段(在BaseFragment中提供了),因为不确定Activity是否需要P层,如果需要Presenter的话,可以在子类中去注入
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        initImmersionBar();
        addActivityToStack();
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarDarkFont(true, 0.1f)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.white)
                .init();
    }

    /**
     * 添加进栈
     */
    private void addActivityToStack() {
        AppActivityManager.getInstance().addActivity(this);
    }

    /**
     * 带打开动画的开启
     */
    public void startActivity(Intent intent, boolean isHaveAnim) {
        startActivity(intent);
        if (isHaveAnim) {
            enterAnim();
        }
    }

    public void enterAnim() {
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    public void exitAnim() {
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
