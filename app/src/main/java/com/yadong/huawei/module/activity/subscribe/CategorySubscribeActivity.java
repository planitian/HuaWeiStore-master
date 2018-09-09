package com.yadong.huawei.module.activity.subscribe;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.dagger.component.DaggerCategorySubscribeComponent;
import com.yadong.huawei.dagger.module.CategorySubscribeModule;
import com.yadong.huawei.model.remote.bean.CategorySubscribeBean;
import com.yadong.huawei.module.activity.web.WebViewActivity;
import com.yadong.huawei.module.adapter.CategorySubscribeAdapter;
import com.yadong.huawei.module.base.BaseMvpActivity;
import com.yadong.huawei.module.widget.recyclerview.adapter.MultiItemTypeAdapter;
import com.yadong.huawei.utils.ToastUtil;

import butterknife.BindView;

/**
 * 预约
 */
public class CategorySubscribeActivity extends BaseMvpActivity<CategorySubscribePresenter>
        implements CategorySubscribeContract.View, MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

//    @Inject
//    CategorySubscribePresenter mPresenter;

    private CategorySubscribeBean mDataModel;

    @Override
    public int setLayout() {
        return R.layout.activity_category_subscribe;
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
        DaggerCategorySubscribeComponent
                .builder()
                .appComponent(getAppComponent())
                .categorySubscribeModule(new CategorySubscribeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        mTitle.setText("预约");
    }

    @Override
    public void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void getDataSuccess(CategorySubscribeBean bean) {
        mDataModel = bean;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategorySubscribeAdapter adapter = new CategorySubscribeAdapter(this);
        adapter.addDataAll(bean.getAppBeanList());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(this, message);
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
    public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("name", mDataModel.getAppBeanList().get(position).getName());
        intent.putExtra("url", mDataModel.getAppBeanList().get(position).getDetailId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        return false;
    }

    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }
}
