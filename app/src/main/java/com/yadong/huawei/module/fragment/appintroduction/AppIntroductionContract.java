package com.yadong.huawei.module.fragment.appintroduction;

import com.yadong.huawei.model.remote.bean.AppIntroductionBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 推荐
 * <p>
 * 契约借口
 */
public interface AppIntroductionContract {

    interface View extends BaseView {
        void getDataSuccess(AppIntroductionBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String packageName);

    }

}
