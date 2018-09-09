package com.yadong.huawei.module.activity.subject;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.dagger.component.DaggerCategorySubjectComponent;
import com.yadong.huawei.dagger.module.CategorySubjectModule;
import com.yadong.huawei.module.adapter.SubjectAdapter;
import com.yadong.huawei.module.base.BaseMvpActivity;
import com.yadong.huawei.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 专题
 */
public class CategorySubjectActivity extends BaseMvpActivity<CategorySubjectPresenter>
        implements CategorySubjectContract.View {


    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

//    @Inject
//    CategorySubjectPresenter mPresenter;

    @Override
    public int setLayout() {
        return R.layout.activity_category_subject;
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
        DaggerCategorySubjectComponent
                .builder()
                .appComponent(getAppComponent())
                .categorySubjectModule(new CategorySubjectModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        mTitle.setText("专题列表");
    }

    @Override
    public void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void getDataSuccess(List<String> list) {
        list.remove(0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubjectAdapter adapter = new SubjectAdapter(this);
        adapter.addDataAll(list);
        mRecyclerView.setAdapter(adapter);
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
    public void finish() {
        super.finish();
        exitAnim();
    }

}
