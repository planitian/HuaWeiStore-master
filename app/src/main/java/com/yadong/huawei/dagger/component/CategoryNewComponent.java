package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategoryNewModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.categorynew.CategoryNewActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = CategoryNewModule.class, dependencies = AppComponent.class)
public interface CategoryNewComponent {
    //这一句,component关联到container
    void inject(CategoryNewActivity activity);

}
