package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.subject.CategorySubjectPresenter;
import com.yadong.huawei.module.activity.subject.CategorySubjectContract;

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
public class CategorySubjectModule {

    private CategorySubjectContract.View mView;

    public CategorySubjectModule(CategorySubjectContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategorySubjectPresenter providePresenter(CategorySubjectContract.View view) {
        return new CategorySubjectPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategorySubjectContract.View provideView() {
        return mView;
    }


}
