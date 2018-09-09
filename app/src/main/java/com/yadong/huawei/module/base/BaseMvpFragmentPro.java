package com.yadong.huawei.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.module.widget.loadsir.callback.Callback;
import com.yadong.huawei.module.widget.loadsir.core.LoadService;
import com.yadong.huawei.module.widget.loadsir.core.LoadSir;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * mvp基类Fragment 加强版
 */
public abstract class BaseMvpFragmentPro<T extends BasePresenter> extends RxFragment
        implements BaseView, Callback.OnReloadListener {


    protected LoadService mBaseLoadService;
    protected BaseMvpActivity mContext;
    protected View mRootView;

    protected boolean mIsLoad = false;//是否加载数据

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseMvpActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null && mBaseLoadService == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            mBaseLoadService = LoadSir.getDefault().register(mRootView, this);
            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews();
        }
        return mBaseLoadService.getLoadLayout();
    }

    @Override
    public void onReload(View v) {
        System.out.println("重新加载  1次");
        updateViews();
    }

    /**
     * 不用这个方式,也可以实现懒加载,调用的方式统一放到了Tab切换的时候,去获取数据,如果已经是成功状态就不获取了,只有是失败或者空的状态才去获取
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated  1ci  " + !mIsLoad);
        if (getUserVisibleHint() && mRootView != null && !mIsLoad) {
            System.out.println("onActivityCreated  1ci");
            mIsLoad = true;
            updateViews();
        }
    }

    /**
     * 为什么要在setUserVisibleHint方法和onActivityCreated两处都调用获取数据进行界面更新的方法updateViews呢？
     * 原因:第一次进入界面，第一个Fragment获取数据是在onActivityCreated里面，剩余的Fragment获取数据是在setUserVisibleHint里面。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        System.out.println("setUserVisibleHint  1ci  " + !mIsLoad);
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsLoad) {
            mIsLoad = true;
            System.out.println("setUserVisibleHint  1次");
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }


    /**
     * 绑定布局文件
     */
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();


    /**
     * 获取 ApplicationComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }


    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    /**
     * 设置当前的状态(用于加载完页面的数据,是成功还是失败)
     */
    public void setCurrentState(Class<? extends Callback> clazz) {
        mBaseLoadService.showCallback(clazz);
    }


}
