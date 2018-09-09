package com.yadong.huawei.module.activity.necessary;

import com.yadong.huawei.model.remote.bean.CategoryNecessaryBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface CategoryNecessaryContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(CategoryNecessaryBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends BasePresenter {
        void getData();

    }

}
