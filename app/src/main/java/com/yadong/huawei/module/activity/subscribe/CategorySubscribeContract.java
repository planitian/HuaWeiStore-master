package com.yadong.huawei.module.activity.subscribe;

import com.yadong.huawei.model.remote.bean.CategorySubscribeBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface CategorySubscribeContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(CategorySubscribeBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends BasePresenter {
        void getData();

    }

}
