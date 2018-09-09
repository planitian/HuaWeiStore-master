package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.categorynew.CategoryNewPresenter;
import com.yadong.huawei.module.activity.categorynew.CategoryNewContract;

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
public class CategoryNewModule {

    private CategoryNewContract.View mView;

    public CategoryNewModule(CategoryNewContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategoryNewPresenter providePresenter(CategoryNewContract.View view) {
        return new CategoryNewPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategoryNewContract.View provideView() {
        return mView;
    }


}
