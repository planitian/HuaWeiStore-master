package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.RankingModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.module.fragment.ranking.RankingFragment;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@FragmentScope
@Component(modules = RankingModule.class, dependencies = AppComponent.class)
public interface RankingComponent {

    //这一句,component关联到container
    void inject(RankingFragment fragment);

}
