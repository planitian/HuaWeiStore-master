package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.RecommendModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.module.fragment.recommend.RecommendFragment;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    //这一句,component关联到container(就是我们的RecommendFragment)
    void inject(RecommendFragment fragment);

}
