package com.yadong.huawei.module.fragment.apprecommend;

import com.yadong.huawei.utils.JsonParseUtils;
import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.bean.AppRecommendBean;
import com.yadong.huawei.model.remote.request.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class AppRecommendPresenter implements AppRecommendContract.Presenter {

    private AppRecommendContract.View mView;
    private ApiService mApiService;

    public AppRecommendPresenter(AppRecommendContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData(String packageName) {
        mApiService
                .getAppRecommendData(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        AppRecommendBean bean = JsonParseUtils.parseAppRecommendBean(string);
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
