package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.category.CategoryContract;
import com.yadong.huawei.module.fragment.category.CategoryPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * 分类
 * <p>
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 * <p>
 * 提供推荐页面的所有对象
 */
@Module
public class CategoryModule {

    private CategoryContract.View mView;

    public CategoryModule(CategoryContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategoryPresenter providePresenter(CategoryContract.View view) {
        return new CategoryPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategoryContract.View provideView() {
        return mView;
    }


}
