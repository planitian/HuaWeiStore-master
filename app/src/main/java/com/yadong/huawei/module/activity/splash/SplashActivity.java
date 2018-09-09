package com.yadong.huawei.module.activity.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yadong.huawei.R;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.module.activity.main.MainActivity;
import com.yadong.huawei.module.base.BaseActivity;
import com.yadong.huawei.utils.Constants;
import com.yadong.huawei.utils.PreferenceUtils;
import com.yadong.huawei.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 闪屏页面
 */
public class SplashActivity extends BaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initViews();
    }

    /**
     * 重写初始化沉浸式状态栏
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarDarkFont(true)
                .init();
    }
    public void initViews() {
        boolean isEnter = PreferenceUtils.getBoolean(Constants.IS_ENTER);
        if (isEnter) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.bt_enter)
    public void onViewClicked() {
        initPermission();
    }

    /**
     * 检查权限
     */
    private void initPermission() {
        //如果版本>6.0才会有权限适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new RxPermissions(this)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                PreferenceUtils.putBoolean(Constants.IS_ENTER, true);
                                ToastUtil.show(App.getInstance(), "授权SD卡成功");

                                startActivity(new Intent(App.getInstance(), MainActivity.class), true);
                                finish();

                            } else {
                                PreferenceUtils.putBoolean(Constants.IS_ENTER, false);
                                ToastUtil.show(App.getInstance(), "没有授权SD卡，可能会影响应用的使用");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Logger.i(throwable.getMessage());
                        }
                    });
        } else {
            PreferenceUtils.putBoolean(Constants.IS_ENTER, true);
            startActivity(new Intent(this, MainActivity.class), true);
            finish();
        }
    }

}
