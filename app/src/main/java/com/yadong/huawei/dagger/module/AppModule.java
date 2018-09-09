package com.yadong.huawei.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * App级别的Module
 */

@Module
public class AppModule {


    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }


    @Provides
    @Singleton
    public Application provideMyApplication() {
        return mApplication;
    }

}
