package com.yadong.huawei.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yadong.huawei.module.widget.LoadingPager;

import butterknife.ButterKnife;

/**
 * 基类Fragment
 */
public abstract class BaseFragment extends RxFragment {

    protected BaseActivity mContext;

    protected LoadingPager mLoadingPager; //缓存Fragment view

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingPager == null) {
            mLoadingPager = new LoadingPager(getContext()) {
                @Override
                public View createSuccessView() {
                    return inflater.inflate(attachLayoutRes(), null);
                }

                @Override
                public void loadData() {
                    BaseFragment.this.updateViews();
                }
            };
            ButterKnife.bind(this, mLoadingPager);
        }
        return mLoadingPager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    /**
     * 绑定布局文件
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();

    /**
     * 进行展示,默认是展示的Loading转圈圈,然后同时去调用loadData方法去加载数据(就是上面创建loadingPager重写的那个loadData方法)
     */
    public void show() {
        if (mLoadingPager != null) {
            mLoadingPager.show();
        }
    }

    /**
     * 设置当前的状态(用于加载完页面的数据,是成功还是失败)
     */
    public void setCurrentState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setCurrentState(result);
        }
    }


}
