package com.yadong.huawei.dagger.score;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 自定义的注解
 * 场景:
 * UserComponent要引用App级别的AppComponent,但是呢AppComponent引用了单例的注解(因为AppComponent引用了AppModule,这个Module中有引用单例注解),
 * Dagger2要求,只要module引用了单例的注解,component也必须要引用这个注解,
 * 但是,如果UserComponent要成功引用AppComponent,又他妈的不能用单例这个注解,所以只能自定义了,然后把这个注解写到UserComponent上,去他妈的,傻逼Dagger
 * <p>
 * <p>
 * 其实这个自定义的注解也是从单例的注解上抄过来了,他妈的
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {

}
