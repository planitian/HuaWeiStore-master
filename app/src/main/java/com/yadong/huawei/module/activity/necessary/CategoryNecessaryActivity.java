package com.yadong.huawei.module.activity.necessary;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.dagger.component.DaggerCategoryNecessaryComponent;
import com.yadong.huawei.dagger.module.CategoryNecessaryModule;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.model.remote.bean.CategoryNecessaryBean;
import com.yadong.huawei.module.activity.appdetail.AppDetailActivity;
import com.yadong.huawei.module.adapter.CategoryNecessaryAdapter;
import com.yadong.huawei.module.adapter.wrapper.CategoryNecessaryTopWrapper;
import com.yadong.huawei.module.base.BaseMvpActivity;
import com.yadong.huawei.module.widget.recyclerview.adapter.MultiItemTypeAdapter;
import com.yadong.huawei.utils.Constants;

import butterknife.BindView;

/**
 * 必备
 */
public class CategoryNecessaryActivity extends BaseMvpActivity<CategoryNecessaryPresenter>
        implements CategoryNecessaryContract.View, MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

//    @Inject
//    CategoryNecessaryPresenter mPresenter;

    private CategoryNecessaryBean mDataModel;

    @Override
    public int setLayout() {
        return R.layout.activity_category_necessary;
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
        DaggerCategoryNecessaryComponent
                .builder()
                .appComponent(getAppComponent())
                .categoryNecessaryModule(new CategoryNecessaryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        mTitle.setText("装机必备");
    }

    @Override
    public void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void getDataSuccess(CategoryNecessaryBean bean) {
        mDataModel = bean;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryNecessaryAdapter adapter = new CategoryNecessaryAdapter(this);
        adapter.addDataAll(bean.getAppBeanList());

        CategoryNecessaryTopWrapper categoryNecessaryTopWrapper = new CategoryNecessaryTopWrapper(this, adapter, bean.getHead());
        mRecyclerView.setAdapter(categoryNecessaryTopWrapper);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void getDataFail(String message) {

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

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        AppBean appBean = mDataModel.getAppBeanList().get(position);
        Intent intent = new Intent(this, AppDetailActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME, appBean.getPackageName());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        return false;
    }
}
