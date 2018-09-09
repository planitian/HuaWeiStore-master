package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.appcomment.AppCommentContract;
import com.yadong.huawei.module.fragment.appcomment.AppCommentPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 */
@Module
public class AppCommentModule {

    private AppCommentContract.View mView;

    public AppCommentModule(AppCommentContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public AppCommentPresenter providePresenter(AppCommentContract.View view) {
        return new AppCommentPresenter(view);
    }


    /**
     * 提供了Contract.View对象
     */
    @Provides
    public AppCommentContract.View provideView() {
        return mView;
    }


}
