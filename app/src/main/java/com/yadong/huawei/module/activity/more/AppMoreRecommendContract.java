package com.yadong.huawei.module.activity.more;

import com.yadong.huawei.model.remote.bean.AppMoreRecommendBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface AppMoreRecommendContract {

    interface View extends BaseView {
        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(AppMoreRecommendBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String type,String packageName);

    }

}
