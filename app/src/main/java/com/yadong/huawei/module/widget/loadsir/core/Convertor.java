package com.yadong.huawei.module.widget.loadsir.core;


import com.yadong.huawei.module.widget.loadsir.callback.Callback;

/**
 */
public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
