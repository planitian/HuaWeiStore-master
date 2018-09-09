package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.recommend.RecommendContract;
import com.yadong.huawei.module.fragment.recommend.RecommendPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * 推荐
 * <p>
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 * <p>
 * 提供推荐页面的所有对象
 */
@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了RecommendPresenter对象
     */
    @Provides
    public RecommendPresenter providePresenter(RecommendContract.View view) {
        return new RecommendPresenter(view);
    }


    /**
     * 提供了RecommendContract.View对象
     */
    @Provides
    public RecommendContract.View provideView() {
        return mView;
    }


}
