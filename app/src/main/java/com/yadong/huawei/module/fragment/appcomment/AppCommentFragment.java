package com.yadong.huawei.module.fragment.appcomment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerAppCommentComponent;
import com.yadong.huawei.dagger.module.AppCommentModule;
import com.yadong.huawei.model.remote.bean.AppCommentBean;
import com.yadong.huawei.module.activity.appdetail.AppDetailActivity;
import com.yadong.huawei.module.adapter.section.AppCommentContactsSection;
import com.yadong.huawei.module.adapter.wrapper.AppCommentTopWrapper;
import com.yadong.huawei.module.base.BaseMvpFragment;
import com.yadong.huawei.module.widget.LoadingPager;
import com.yadong.huawei.module.widget.recyclerview.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * App详情页面_评论fragment
 */
public class AppCommentFragment extends BaseMvpFragment<AppCommentPresenter>
        implements AppCommentContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AppCommentBean mAppCommentBean;
    private List<AppCommentBean.CommentsBean> mHotCommentList = new ArrayList<>();//精彩评论的list
    private List<AppCommentBean.CommentsBean> mAllCommentList = new ArrayList<>();//全部评论的list

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_app_comment;
    }

    @Override
    protected void initInjector() {
        DaggerAppCommentComponent
                .builder()
                .appComponent(getAppComponent())
                .appCommentModule(new AppCommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        mPresenter.getData(((AppDetailActivity) getActivity()).getAppPackageName());
    }


    @Override
    public void getDataSuccess(AppCommentBean bean) {
        this.mAppCommentBean = bean;
        showRevData();
        //        mBaseLoadService.showSuccess();
        setCurrentState(LoadingPager.LoadResult.success);
    }

    /**
     * 展示列表数据
     */
    private void showRevData() {
        mHotCommentList.clear();
        mAllCommentList.clear();

        for (AppCommentBean.CommentsBean commentsBean : mAppCommentBean.getComments()) {
            //type为1是精彩评论
            if (commentsBean.getCommentType().equals("1")) {
                mHotCommentList.add(commentsBean);
            } else {
                mAllCommentList.add(commentsBean);
            }
        }

        //session多条目部分
        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());
        if (mHotCommentList.size() > 0) {
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "精彩评论", mHotCommentList));
        }
        if (mAllCommentList.size() > 0) {
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "全部评论", mAllCommentList));
        }

        //头部分
        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(), sectionAdapter);
        appCommentTopWrapper.addDataAll(mAppCommentBean);

        //设置给recyclerView
        mRecyclerView.setAdapter(appCommentTopWrapper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(getContext(), message);
        //        setCurrentState(ErrorCallback.class);
        setCurrentState(LoadingPager.LoadResult.error);
    }


}
