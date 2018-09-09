package com.yadong.huawei.module.activity.categorynew;

import com.yadong.huawei.model.remote.bean.CategoryNewBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface CategoryNewContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(CategoryNewBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends BasePresenter {
        void getData();

    }

}
