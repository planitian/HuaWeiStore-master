package com.yadong.huawei.module.fragment.apprecommend;

import com.yadong.huawei.model.remote.bean.AppRecommendBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface AppRecommendContract {

    interface View extends BaseView {
        void getDataSuccess(AppRecommendBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String packageName);

    }

}
