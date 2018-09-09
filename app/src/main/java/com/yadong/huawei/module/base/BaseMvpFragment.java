package com.yadong.huawei.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.dagger.component.AppComponent;

import javax.inject.Inject;

/**
 * mvp的基类Fragment
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment
        implements BaseView {

    /**
     * 把 Presenter 提取到基类需要配合基类的 initInjector() 进行注入，如果继承这个基类则必定要提供一个 Presenter 注入方法，
     * 该APP所有 Presenter 都是在 Module 提供注入实现，也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInjector();
        initViews();
    }

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();


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

}
