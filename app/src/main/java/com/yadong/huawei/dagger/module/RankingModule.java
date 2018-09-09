package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.ranking.RankingContract;
import com.yadong.huawei.module.fragment.ranking.RankingPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * 分类
 * <p>
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 * <p>
 * 提供排行页面的所有对象
 */
@Module
public class RankingModule {

    private RankingContract.View mView;

    public RankingModule(RankingContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public RankingPresenter providePresenter(RankingContract.View view) {
        return new RankingPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public RankingContract.View provideView() {
        return mView;
    }


}
