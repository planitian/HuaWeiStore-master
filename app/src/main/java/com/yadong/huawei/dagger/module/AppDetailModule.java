package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.appdetail.AppDetailPresenter;
import com.yadong.huawei.module.activity.appdetail.AppDetailContract;

import dagger.Module;
import dagger.Provides;


/**
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 * <p>
 * 提供推荐页面的所有对象
 */
@Module
public class AppDetailModule {

    private AppDetailContract.View mView;

    public AppDetailModule(AppDetailContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public AppDetailPresenter providePresenter(AppDetailContract.View view) {
        return new AppDetailPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public AppDetailContract.View provideView() {
        return mView;
    }


}
