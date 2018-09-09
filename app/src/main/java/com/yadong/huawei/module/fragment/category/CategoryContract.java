package com.yadong.huawei.module.fragment.category;

import com.yadong.huawei.model.remote.bean.CategoryBean;
import com.yadong.huawei.module.base.BasePresenter;
import com.yadong.huawei.module.base.BaseView;

/**
 * 分类
 * <p>
 * 契约借口
 */
public interface CategoryContract {

    interface View extends BaseView {


        void getDataSuccess(CategoryBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends BasePresenter {

        void getData();

    }

}
