package com.yadong.huawei.module.base;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 基础 BaseView 接口
 */
public interface BaseView {

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
