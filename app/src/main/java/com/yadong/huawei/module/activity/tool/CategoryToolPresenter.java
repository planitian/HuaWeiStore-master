package com.yadong.huawei.module.activity.tool;

import com.yadong.huawei.utils.JsonParseUtils;
import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.bean.CategoryToolBean;
import com.yadong.huawei.model.remote.request.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class CategoryToolPresenter implements CategoryToolContract.Presenter {


    private CategoryToolContract.View mView;
    private ApiService mApiService;

    public CategoryToolPresenter(CategoryToolContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {
        mView.showLoading();
        mApiService
                .getCategoryToolData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        CategoryToolBean detailBean = JsonParseUtils.parseCategoryToolBean(string);
                        mView.getDataSuccess(detailBean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.hideLoading();
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }
}
