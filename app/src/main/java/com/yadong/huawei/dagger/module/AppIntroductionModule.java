package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.appintroduction.AppIntroductionContract;
import com.yadong.huawei.module.fragment.appintroduction.AppIntroductionPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * 推荐
 * <p>
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 */
@Module
public class AppIntroductionModule {

    private AppIntroductionContract.View mView;

    public AppIntroductionModule(AppIntroductionContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public AppIntroductionPresenter providePresenter(AppIntroductionContract.View view) {
        return new AppIntroductionPresenter(view);
    }


    /**
     * 提供了Contract.View对象
     */
    @Provides
    public AppIntroductionContract.View provideView() {
        return mView;
    }


}
