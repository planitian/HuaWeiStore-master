package com.yadong.huawei.module.activity.apkmanager;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApkManagerActivity extends BaseActivity {


    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.progressImg)
    ImageView progressImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_management);
        ButterKnife.bind(this);
        initViews();
        updateViews();
    }

    /**
     * 重写初始化沉浸式状态栏
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.tab_background)
                .init();
    }

    public void initViews() {
        mTitleText.setText("安装包管理");
    }

    public void updateViews() {

    }

    @OnClick(R.id.nodata_localpkg_refresh)
    public void onViewClicked() {
        progressImg.setVisibility(View.VISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) progressImg.getDrawable();
        drawable.start();
    }


    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }

    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }
}
