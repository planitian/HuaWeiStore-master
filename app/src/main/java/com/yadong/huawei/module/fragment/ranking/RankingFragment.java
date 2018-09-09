package com.yadong.huawei.module.fragment.ranking;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.utils.Constants;
import com.yadong.huawei.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerRankingComponent;
import com.yadong.huawei.dagger.module.RankingModule;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.model.remote.bean.TopBean;
import com.yadong.huawei.module.activity.appdetail.AppDetailActivity;
import com.yadong.huawei.module.adapter.section.RankingSection;
import com.yadong.huawei.module.adapter.wrapper.RankingTopWrapper;
import com.yadong.huawei.module.base.BaseMvpFragment;
import com.yadong.huawei.module.widget.LoadingPager;
import com.yadong.huawei.module.widget.ViewUpSearch;
import com.yadong.huawei.module.widget.recyclerview.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

/**
 * 排行
 */
public class RankingFragment extends BaseMvpFragment<RankingPresenter>
        implements RankingContract.View, RankingSection.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.view_up_search)
    ViewUpSearch mViewUpSearch;

    private boolean mIsExpand = true;//是否展开,默认是展开的

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ranking;
    }

    @Override
    protected void initInjector() {
        DaggerRankingComponent
                .builder()
                .appComponent(getAppComponent())
                .rankingModule(new RankingModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void updateViews() {
        mPresenter.getData();
    }


    @Override
    public void getDataSuccess(TopBean bean) {
        setCurrentState(LoadingPager.LoadResult.success);
        showRevData(bean);
        setSearchListener();
    }

    /**
     * 展示RecyclerView列表
     */
    private void showRevData(TopBean bean) {
        //创建带Section的Adapter
        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());

        Map<String, List<AppBean>> appBeanMap = bean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();

        //体数据  包含:标题和内容List
        for (String name : strings) {
            List<AppBean> appBeanList = appBeanMap.get(name);
            RankingSection section = new RankingSection(getContext(), name, appBeanList);
            section.setOnItemClickListener(this);//条目的点击事件的监听

            sectionAdapter.addSection(section);
        }

        //头
        RankingTopWrapper topTopWrapper = new RankingTopWrapper(getContext(), sectionAdapter);
        topTopWrapper.addDataAll(bean.getTopTopBeanList());

        mRecyclerView.setAdapter(topTopWrapper);
    }

    /**
     * 设置搜索框的显示,监听RecyclerView
     */
    private void setSearchListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = ((LinearLayoutManager) (mRecyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                /**
                 * 1.是第一个条目 2.是展开状态 3.dy>0:是向上滑动
                 * 搜索框应该隐藏,标记也跟着改
                 * firstVisibleItemPosition==0 并且 mIsExpand==true 并且 dy>0
                 */
                if (firstVisibleItemPosition == 0 && mIsExpand && dy > 0) {
                    mViewUpSearch.updateShow(false);
                    mIsExpand = false;
                }
                /**
                 * 1.是第一个条目 2.没有展开 3.dy<0:是向下滑动
                 * 搜索框应显示
                 * firstVisibleItemPosition==0 并且 mIsExpand==false 并且 dy>0
                 */
                else if (firstVisibleItemPosition == 0 && !mIsExpand && dy < 0) {
                    mViewUpSearch.updateShow(true);
                    mIsExpand = true;
                }
            }
        });
    }

    @Override
    public void getDataFail(String message) {
        setCurrentState(LoadingPager.LoadResult.error);
        ToastUtil.show(getContext(), message);
    }

    @Override
    public void onItemClick(int position, String packageName) {
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME, packageName);
        mContext.startActivity(intent);
    }
}
