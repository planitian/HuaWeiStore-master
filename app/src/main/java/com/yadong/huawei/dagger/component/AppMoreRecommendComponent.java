package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.AppMoreRecommendModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.more.AppMoreRecommendActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = AppMoreRecommendModule.class, dependencies = AppComponent.class)
public interface AppMoreRecommendComponent {

    //这一句,component关联到container
    void inject(AppMoreRecommendActivity activity);

}
