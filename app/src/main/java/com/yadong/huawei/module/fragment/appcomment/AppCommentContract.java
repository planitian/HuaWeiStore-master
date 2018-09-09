package com.yadong.huawei.module.fragment.appcomment;

import com.yadong.huawei.model.remote.bean.AppCommentBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 契约借口
 */
public interface AppCommentContract {

    interface View extends BaseView {
        void getDataSuccess(AppCommentBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String packageName);

    }

}
