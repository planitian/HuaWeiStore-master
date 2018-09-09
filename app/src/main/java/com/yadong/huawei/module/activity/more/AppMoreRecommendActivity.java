package com.yadong.huawei.module.activity.more;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.dagger.component.DaggerAppMoreRecommendComponent;
import com.yadong.huawei.dagger.module.AppMoreRecommendModule;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.model.remote.bean.AppMoreRecommendBean;
import com.yadong.huawei.module.activity.appdetail.AppDetailActivity;
import com.yadong.huawei.module.adapter.MoreRecommendAdapter;
import com.yadong.huawei.module.base.BaseMvpActivity;
import com.yadong.huawei.module.widget.recyclerview.adapter.MultiItemTypeAdapter;
import com.yadong.huawei.utils.Constants;
import com.yadong.huawei.utils.ToastUtil;

import butterknife.BindView;

public class AppMoreRecommendActivity extends BaseMvpActivity<AppMoreRecommendPresenter>
        implements AppMoreRecommendContract.View, MultiItemTypeAdapter.OnItemClickListener<AppBean> {

    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

//    @Inject
//    AppMoreRecommendPresenter mPresenter;

    private String type;
    private String packageName;

    @Override
    public int setLayout() {
        return R.layout.activity_app_more_recommend;
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
    public void initInjector() {
        DaggerAppMoreRecommendComponent
                .builder()
                .appComponent(getAppComponent())
                .appMoreRecommendModule(new AppMoreRecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        type = getIntent().getStringExtra("type");
        packageName = getIntent().getStringExtra("packageName");

        switch (type){
            case "popular":
                mTitle.setText("流行应用");
                break;
            case "taste":
                mTitle.setText("兴趣相近的用户也安装了");
                break;
            case "hot":
                mTitle.setText("本周热议的应用");
                break;
        }
    }

    @Override
    public void updateViews() {
        mPresenter.getData(type,packageName);
    }

    @Override
    public void getDataSuccess(AppMoreRecommendBean bean) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MoreRecommendAdapter adapter = new MoreRecommendAdapter(this);
        adapter.addDataAll(bean.getMoreAppBean());
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(this,message);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, AppBean bean, int position) {
        Intent intent = new Intent(this,AppDetailActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME,bean.getPackageName());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, AppBean o, int position) {
        return false;
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
    public void finish() {
        super.finish();
        exitAnim();
    }
}
