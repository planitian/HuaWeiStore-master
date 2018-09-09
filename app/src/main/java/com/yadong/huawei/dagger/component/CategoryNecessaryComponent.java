package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategoryNecessaryModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.necessary.CategoryNecessaryActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = CategoryNecessaryModule.class, dependencies = AppComponent.class)
public interface CategoryNecessaryComponent {

    //这一句,component关联到container
    void inject(CategoryNecessaryActivity activity);

}
