package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.necessary.CategoryNecessaryPresenter;
import com.yadong.huawei.module.activity.necessary.CategoryNecessaryContract;

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
public class CategoryNecessaryModule {

    private CategoryNecessaryContract.View mView;

    public CategoryNecessaryModule(CategoryNecessaryContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategoryNecessaryPresenter providePresenter(CategoryNecessaryContract.View view) {
        return new CategoryNecessaryPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategoryNecessaryContract.View provideView() {
        return mView;
    }


}
