package com.yadong.huawei.module.activity.more;

import com.yadong.huawei.utils.JsonParseUtils;
import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.bean.AppMoreRecommendBean;
import com.yadong.huawei.model.remote.request.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class AppMoreRecommendPresenter implements AppMoreRecommendContract.Presenter {


    private AppMoreRecommendContract.View mView;
    private ApiService mApiService;

    public AppMoreRecommendPresenter(AppMoreRecommendContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData(String type,String packageName) {
        mView.showLoading();
        mApiService
                .getAppMoreRecommendData(type,packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        AppMoreRecommendBean detailBean = JsonParseUtils.parseAppMoreRecommendBean(string);
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
