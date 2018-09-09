package com.yadong.huawei.module.activity.appdetail;

import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.factory.AppDetailFragmentFactory;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.dagger.component.DaggerAppDetailComponent;
import com.yadong.huawei.dagger.module.AppDetailModule;
import com.yadong.huawei.model.remote.bean.AppDetailBean;
import com.yadong.huawei.module.adapter.AppDetailPagerAdapter;
import com.yadong.huawei.module.base.BaseMvpFragment;
import com.yadong.huawei.module.base.BaseMvpActivity;
import com.yadong.huawei.module.widget.DownloadProgressButton;
import com.yadong.huawei.module.widget.SubTabNavigator;
import com.yadong.huawei.utils.AppInfoUtils;
import com.yadong.huawei.utils.Constants;
import com.yadong.huawei.utils.ImageLoader;
import com.yadong.huawei.utils.ToastUtil;
import com.yadong.huawei.utils.UIUtils;
import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.utils.DbDownUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * App详情页面
 */
public class AppDetailActivity extends BaseMvpActivity<AppDetailPresenter>
        implements AppDetailContract.View, HttpDownManager.DownloadObserver {

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    @BindView(R.id.detail_download_button)
    DownloadProgressButton mDownloadButton;

    @BindView(R.id.detail_download_share_container)
    LinearLayout mShareContainer;

    @BindView(R.id.detail_download_comment_container)
    LinearLayout mCommentContainer;

    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView mAppIcon;

    @BindView(R.id.detail_head_app_name_textview)
    TextView mAppName;

    @BindView(R.id.detail_head_download_count_textview)
    TextView mAppCount;

    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar mAppStarsRatingBar;

    @BindView(R.id.detail_head_label_icon_container)
    LinearLayout mLabelIconContainer;

    @BindView(R.id.detail_head_label_folding_textview)
    TextView mLabelFolding;

    @BindView(R.id.detail_head_label_container)
    RelativeLayout mLabelContainer;

    @BindView(R.id.detail_head_safe_icon_container)
    LinearLayout mSafeIconContainer;

    @BindView(R.id.detail_head_safe_icon_total_container)
    LinearLayout mSafeIconTotalContainer;

    @BindView(R.id.sub_tab)
    SubTabNavigator mSubTab;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

//    @Inject
//    AppDetailPresenter mPresenter;

    private DbDownUtil dbUtil;
    private HttpDownManager manager;
    private DownInfo downInfo;
    private File outputFile;

    private boolean mExpand;//是否展开
    private String mPackageName;//传递过来的包名参数
    private AppDetailBean mDetailBean;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void initInjector() {
        DaggerAppDetailComponent
                .builder()
                .appComponent(getAppComponent())
                .appDetailModule(new AppDetailModule(this))
                .build()
                .inject(this);
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

    @Override
    public void initViews() {
        getIntentData();

        manager = HttpDownManager.getInstance();
        manager.registerObserver(this);
        dbUtil = DbDownUtil.getInstance();

        downInfo = dbUtil.queryDownBy(mPackageName.hashCode());
        if (downInfo == null) {
            outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), mPackageName + ".apk");
        }
    }

    private void getIntentData() {
        mPackageName = getIntent().getStringExtra(Constants.PACKAGE_NAME);
    }

    @Override
    public void updateViews() {
        mPresenter.getData(mPackageName);
    }

    @Override
    public void getDataSuccess(AppDetailBean bean) {
        this.mDetailBean = bean;
        setDetail();
    }

    /**
     * 设置详情
     */
    private void setDetail() {
        setHead();
        setLabel();
        setSafeLabel();
        setSubTab();
        setDetailDown();
    }

    /**
     * 设置App 头详情
     */
    private void setHead() {
        ImageLoader.load(this, mDetailBean.getIcoUrl(), mAppIcon, R.drawable.icon_default_bg);
        mAppName.setText(mDetailBean.getName());
        mAppCount.setText(mDetailBean.getIntro());
        mAppStarsRatingBar.setRating(Float.parseFloat(mDetailBean.getStars()));
    }

    /**
     * 设置横向标签内容
     */
    private void setLabel() {
        if (EmptyUtils.isNotEmpty(mDetailBean.getLabelNameList())) {
            for (AppDetailBean.LabelName labelNamesBean : mDetailBean.getLabelNameList()) {
                View labelView = UIUtils.inflate(R.layout.appdetail_item_head_label_item);
                TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
                label.setText(labelNamesBean.getName());
                if (labelNamesBean.getType() == 1) {
                    label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
                }
                mLabelIconContainer.addView(labelView);
            }
        }
    }

    /**
     * 设置安全检测的标签内容
     */
    private void setSafeLabel() {
        if (EmptyUtils.isNotEmpty(mDetailBean.getSafeLabelList())) {
            for (AppDetailBean.SafeLabel safeLabelsBean : mDetailBean.getSafeLabelList()) {
                View safeLabelView = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);

                TextView safeChecker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
                TextView safeCheckerLabel = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
                TextView desc = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);
                ImageView appIcon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);

                safeChecker.setText(safeLabelsBean.getDetectorName());
                safeCheckerLabel.setText(safeLabelsBean.getDetectorDesc());
                desc.setText(safeLabelsBean.getName());
                ImageLoader.load(this, safeLabelsBean.getUrl(), appIcon, R.drawable.icon_default_bg);

                mSafeIconContainer.addView(safeLabelView);
            }
        }
    }

    /**
     * 设置子选项
     */
    private void setSubTab() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(AppDetailFragmentFactory.createFragment(AppDetailFragmentFactory.TAB_APP_INTRODUCTION));
        fragments.add(AppDetailFragmentFactory.createFragment(AppDetailFragmentFactory.TAB_APP_COMMENT));
        fragments.add(AppDetailFragmentFactory.createFragment(AppDetailFragmentFactory.TAB_APP_RECOMMEND));

        AppDetailPagerAdapter appDetailPagerAdapter = new AppDetailPagerAdapter(getSupportFragmentManager());
        appDetailPagerAdapter.setFragments(fragments);

        mViewPager.setAdapter(appDetailPagerAdapter);
        mViewPager.addOnPageChangeListener(mSubTab);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                BaseMvpFragment fragment = AppDetailFragmentFactory.createFragment(position);
                fragment.show();
            }
        });

        mSubTab.setViewPager(mViewPager);

        mSubTab.setListener(new SubTabNavigator.OnItemClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (int) view.getTag();
                switch (tag) {

                    case SubTabNavigator.TAG_LEFT_VIEW:
                        showLeftView();
                        break;

                    case SubTabNavigator.TAG_NONE_VIEW:
                        showNoneView();
                        break;

                    case SubTabNavigator.TAG_RIGHT_VIEW:
                        showRightView();
                        break;
                }
            }
        });
    }

    private void showLeftView() {
        mSubTab.setCurrentPosition(SubTabNavigator.TAG_LEFT_VIEW);
    }

    private void showNoneView() {
        mSubTab.setCurrentPosition(SubTabNavigator.TAG_NONE_VIEW);
    }

    private void showRightView() {
        mSubTab.setCurrentPosition(SubTabNavigator.TAG_RIGHT_VIEW);
    }

    /**
     * 设置下载按钮的详情
     */
    private void setDetailDown() {
        if (downInfo == null) {
            mDownloadButton.setStartText("下载 " + Formatter.formatFileSize(UIUtils.getContext(),
                    Long.parseLong(mDetailBean.getSize())));
        } else {
            if (downInfo.getState() == DownState.DOWN) {
                mDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                manager.startDown(downInfo);
            } else if (downInfo.getState() == DownState.PAUSE) {
                mDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
            } else if (downInfo.getState() == DownState.FINISH) {
                mDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_FINISH);
            }
            mDownloadButton.setProgress((int) (100 * downInfo.getReadLength() / downInfo.getCountLength()));
        }

        mDownloadButton.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                manager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                AppInfoUtils.install(downInfo.getSavePath());
                                if (dbUtil != null && downInfo != null)
                                    dbUtil.update(downInfo);
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onLoadingTask() {
                mDownloadButton.setMax(100);

                if (downInfo == null) {
                    downInfo = new DownInfo(mDetailBean.getDownloadUrl());
                    downInfo.setId((long) mPackageName.hashCode());
                    downInfo.setState(DownState.START);
                    downInfo.setSavePath(outputFile.getAbsolutePath());
                    dbUtil.save(downInfo);

                }
                if (downInfo.getState() != DownState.FINISH) {
                    manager.startDown(downInfo);
                }
            }
        });
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(this, message);
    }

    @OnClick({R.id.iv_search, R.id.detail_download_share_container,
            R.id.detail_download_comment_container,
            R.id.detail_head_label_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                ToastUtil.show(this, "搜索");
                break;

            case R.id.detail_download_share_container:
                ToastUtil.show(this, "分享");
                break;

            case R.id.detail_download_comment_container:
                ToastUtil.show(this, "评论");
                break;

            case R.id.detail_head_label_container:
                expandLabelDetail();
                break;
        }
    }

    /**
     * 展开标签下的详情
     */
    private void expandLabelDetail() {
        if (mExpand) {
            mExpand = false;
            mSafeIconTotalContainer.setVisibility(View.GONE);
            mLabelFolding.setBackgroundResource(R.drawable.ic_public_arrow_down);
        } else {
            mExpand = true;
            mSafeIconTotalContainer.setVisibility(View.VISIBLE);
            mLabelFolding.setBackgroundResource(R.drawable.ic_public_arrow_up);
        }
    }

    public String getAppPackageName() {
        return mPackageName;
    }

    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.pause(downInfo);
            manager.unRegisterObserver(this);
            if (downInfo != null) {
                dbUtil.update(downInfo);
            }
        }
        AppDetailFragmentFactory.removeAll();
    }

    @Override
    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }

    @Override
    public void onDownloadStateChanged(DownInfo downInfo) {

    }

    @Override
    public void onDownloadProgressed(DownInfo info) {
        if (downInfo != null && info.getId() == downInfo.getId()) {
            mDownloadButton.setProgress((int) (100 * info.getReadLength() / info.getCountLength()));
        }
    }
}
