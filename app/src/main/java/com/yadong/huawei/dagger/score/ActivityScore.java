package com.yadong.huawei.dagger.score;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 单例标示
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScore {
}
