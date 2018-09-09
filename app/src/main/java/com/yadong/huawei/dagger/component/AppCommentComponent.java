package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.AppCommentModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.module.fragment.appcomment.AppCommentFragment;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */
//这一句,component关联到module
@FragmentScope
@Component(modules = AppCommentModule.class, dependencies = AppComponent.class)
public interface AppCommentComponent {

    //这一句,component关联到container
    void inject(AppCommentFragment fragment);

}
