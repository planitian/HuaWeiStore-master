package com.yadong.huawei.module.activity.subject;

import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

import java.util.List;

/**
 * 契约借口
 */
public interface CategorySubjectContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(List<String> list);

        void getDataFail(String message);

    }

    interface Presenter  extends BasePresenter{
        void getData();

    }

}
