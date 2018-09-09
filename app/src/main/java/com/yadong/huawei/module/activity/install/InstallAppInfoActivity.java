package com.yadong.huawei.module.activity.install;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.model.remote.bean.AppInfo;
import com.yadong.huawei.module.adapter.AppInfoAdapter;
import com.yadong.huawei.module.base.BaseActivity;
import com.yadong.huawei.module.widget.recyclerview.adapter.MultiItemTypeAdapter;
import com.yadong.huawei.utils.AppInfoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstallAppInfoActivity extends BaseActivity
        implements MultiItemTypeAdapter.OnItemClickListener, AppInfoAdapter.OnItemDetailClickListener {


    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<AppInfo> mAppInfoList;
    private AppInfoAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_app_info);
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
        mTitle.setText("安装管理");
    }

    public void updateViews() {
        showRevData();
    }

    private void showRevData() {
        mAppInfoList = AppInfoUtils.getAppInfos(this);
        mAdapter = new AppInfoAdapter(this);
        mAdapter.addDataAll(mAppInfoList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
        mAdapter.setItemClickListener(this);
    }

    /**
     * 卸载应用
     * @param position
     * @param packageName
     */
    @Override
    public void onUninstallApp(int position, String packageName) {
        AppUtils.uninstallApp(packageName);
    }

    /**
     * 管理应用
     * @param position
     * @param packageName
     */
    @Override
    public void onShowInstalledAppDetail(int position, String packageName) {
        AppInfoUtils.showInstalledAppDetails(this,packageName);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        AppInfoUtils.openApplication(this, mAppInfoList.get(position).getPackageName());
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        return false;
    }


    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }
    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }
}
