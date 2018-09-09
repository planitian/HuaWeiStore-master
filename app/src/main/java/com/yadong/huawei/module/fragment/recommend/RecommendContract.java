package com.yadong.huawei.module.fragment.recommend;

import com.yadong.huawei.model.remote.bean.RecommendBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 推荐
 * <p>
 * 契约借口
 */
public interface RecommendContract {

    interface View extends BaseView {

        void getDataSuccess(RecommendBean recommendBean);

        void getDataFail(String message);

        void getDataMoreSuccess(RecommendBean recommendBean);
    }

    interface Presenter extends BasePresenter {

        void getRecommendDataMore();

        void getData();
    }

}
