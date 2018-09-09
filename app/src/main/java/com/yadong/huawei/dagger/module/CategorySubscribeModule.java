package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.subscribe.CategorySubscribePresenter;
import com.yadong.huawei.module.activity.subscribe.CategorySubscribeContract;

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
public class CategorySubscribeModule {

    private CategorySubscribeContract.View mView;

    public CategorySubscribeModule(CategorySubscribeContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategorySubscribePresenter providePresenter(CategorySubscribeContract.View view) {
        return new CategorySubscribePresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategorySubscribeContract.View provideView() {
        return mView;
    }


}
