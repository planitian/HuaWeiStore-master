package com.yadong.huawei.module.fragment.manage;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.module.activity.apkmanager.ApkManagerActivity;
import com.yadong.huawei.module.activity.clean.CleanCacheActivity;
import com.yadong.huawei.module.activity.install.InstallAppInfoActivity;
import com.yadong.huawei.module.base.BaseFragment;
import com.yadong.huawei.module.widget.EnterLayout;
import com.yadong.huawei.module.widget.LoadingPager;
import com.yadong.huawei.utils.ToastUtil;
import com.yadong.huawei.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 管理
 */
public class ManageFragment extends BaseFragment {

    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel;

    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle;

    @BindView(R.id.install_manager_layout)
    EnterLayout installManager;

    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager;

    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager;

    @BindView(R.id.connect_computer)
    EnterLayout connect;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void initViews() {
        tvUpdateLabel.setText(UIUtils.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtils.getString(R.string.update_manager_subtitle_version_new));
        installManager.setTitle(UIUtils.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtils.getString(R.string.install_manager_subtitle));
        apkManager.setTitle(UIUtils.getString(R.string.apk_management));
        apkManager.setMemo(UIUtils.getString(R.string.apkmanage_tips_modify));
        systemManager.setTitle(UIUtils.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtils.getString(R.string.system_manager_memo));
        connect.setTitle(UIUtils.getString(R.string.connect_pc));
        connect.setMemo(UIUtils.getString(R.string.manager_phone_by_pc));
    }

    @Override
    protected void updateViews() {
        setCurrentState(LoadingPager.LoadResult.success);
    }


    @OnClick({R.id.install_manager_layout, R.id.apk_manager_layout,
            R.id.system_manager_layout, R.id.connect_computer, R.id.update_manager_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //安装管理
            case R.id.install_manager_layout:
                startActivity(new Intent(getContext(), InstallAppInfoActivity.class));
                break;
            //安装包管理
            case R.id.apk_manager_layout:
                startActivity(new Intent(getContext(), ApkManagerActivity.class));
                break;

            //空间清理
            case R.id.system_manager_layout:
                startActivity(new Intent(getContext(), CleanCacheActivity.class));
                break;

            case R.id.connect_computer:
                ToastUtil.show(getContext(), "连接电脑");
                break;

            case R.id.update_manager_layout:
                ToastUtil.show(getContext(), "更新管理");
                break;
        }
    }
}
