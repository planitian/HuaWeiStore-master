package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.apprecommend.AppRecommendContract;
import com.yadong.huawei.module.fragment.apprecommend.AppRecommendPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 */
@Module
public class AppRecommendModule {

    private AppRecommendContract.View mView;

    public AppRecommendModule(AppRecommendContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public AppRecommendPresenter providePresenter(AppRecommendContract.View view) {
        return new AppRecommendPresenter(view);
    }


    /**
     * 提供了Contract.View对象
     */
    @Provides
    public AppRecommendContract.View provideView() {
        return mView;
    }


}
