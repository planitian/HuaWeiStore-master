package com.yadong.huawei.module.fragment.category;

import com.yadong.huawei.utils.JsonParseUtils;
import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.bean.CategoryBean;
import com.yadong.huawei.model.remote.request.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private ApiService mApiService;

    public CategoryPresenter(CategoryContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {
        mApiService
                .getCategoryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        CategoryBean bean = JsonParseUtils.parseCategoryBean(string);
                        mView.getDataSuccess(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getDataFail(throwable.getMessage());
                    }
                });


    }
}
