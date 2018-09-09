package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategorySubjectModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.subject.CategorySubjectActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = CategorySubjectModule.class, dependencies = AppComponent.class)
public interface CategorySubjectComponent {
    //这一句,component关联到container
    void inject(CategorySubjectActivity activity);
}
