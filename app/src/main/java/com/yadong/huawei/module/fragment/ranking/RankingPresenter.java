package com.yadong.huawei.module.fragment.ranking;

import com.yadong.huawei.utils.JsonParseUtils;
import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.bean.TopBean;
import com.yadong.huawei.model.remote.request.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class RankingPresenter implements RankingContract.Presenter {

    private RankingContract.View mView;
    private ApiService mApiService;

    public RankingPresenter(RankingContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {
        mApiService
                .getTopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        TopBean topBean = JsonParseUtils.parseTopBean(string);
                        mView.getDataSuccess(topBean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }
}
