package com.yadong.huawei.common.manager;


/**
 * 全局变量的管理者
 */
public class GlobalParamManager {


    private GlobalParamManager() {
    }

    public static GlobalParamManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static GlobalParamManager INSTANCE = new GlobalParamManager();
    }


}
