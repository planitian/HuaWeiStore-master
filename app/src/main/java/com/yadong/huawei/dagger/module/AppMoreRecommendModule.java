package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.more.AppMoreRecommendPresenter;
import com.yadong.huawei.module.activity.more.AppMoreRecommendContract;

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
public class AppMoreRecommendModule {

    private AppMoreRecommendContract.View mView;

    public AppMoreRecommendModule(AppMoreRecommendContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public AppMoreRecommendPresenter providePresenter(AppMoreRecommendContract.View view) {
        return new AppMoreRecommendPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public AppMoreRecommendContract.View provideView() {
        return mView;
    }


}
