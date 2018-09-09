package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.AppIntroductionModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.module.fragment.appintroduction.AppIntroductionFragment;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */
//这一句,component关联到module
@FragmentScope
@Component(modules = AppIntroductionModule.class, dependencies = AppComponent.class)
public interface AppIntroductionComponent {

    //这一句,component关联到container
    void inject(AppIntroductionFragment fragment);

}
