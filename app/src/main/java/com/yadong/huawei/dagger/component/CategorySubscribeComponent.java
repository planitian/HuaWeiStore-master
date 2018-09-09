package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategorySubscribeModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.subscribe.CategorySubscribeActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = CategorySubscribeModule.class, dependencies = AppComponent.class)
public interface CategorySubscribeComponent {

    //这一句,component关联到container
    void inject(CategorySubscribeActivity activity);

}
