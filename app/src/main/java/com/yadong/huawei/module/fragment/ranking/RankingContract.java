package com.yadong.huawei.module.fragment.ranking;

import com.yadong.huawei.model.remote.bean.TopBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 排行
 * <p>
 * 契约借口
 */
public interface RankingContract {

    interface View extends BaseView {
        void getDataSuccess(TopBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }

}
