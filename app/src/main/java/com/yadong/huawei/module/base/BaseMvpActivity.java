package com.yadong.huawei.module.base;

import android.os.Bundle;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.dagger.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * MVP的基类Activity
 */
public abstract class BaseMvpActivity<T extends BasePresenter>
        extends BaseActivity implements BaseView {


    /**
     * 把 Presenter 提取到基类需要配合基类的 initInjector() 进行注入，如果继承这个基类则必定要提供一个 Presenter 注入方法，
     * 该APP所有 Presenter 都是在 Module 提供注入实现，也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initButterKnife();
        initInjector();
        initViews();
        updateViews();
    }

    /**
     * 设置布局
     */
    public abstract int setLayout();

    /**
     * 添加Activity进栈和初始化ButterKnife绑定控件
     */
    private void initButterKnife() {
        ButterKnife.bind(this);
    }

    /**
     * 用dagger进行注入
     */
    public abstract void initInjector();

    /**
     * 初始化
     */
    public abstract void initViews();

    /**
     * 更新视图控件
     */
    public abstract void updateViews();


    /**
     * 获取 ApplicationComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    /**
     * 绑定生命周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }



}
