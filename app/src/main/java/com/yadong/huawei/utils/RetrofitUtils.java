package com.yadong.huawei.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.model.remote.request.ApiService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具管理类
 */

public class RetrofitUtils {

    private Retrofit retrofit;
    private static ApiService mApiService;

    //设缓存有效期为1天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;


    public void initOkHttp(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //CommonParamsInterceptor interceptor = new CommonParamsInterceptor(context, new Gson());
        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)       //设置连接超时
                .readTimeout(10, TimeUnit.SECONDS)          //设置读取超时
                .writeTimeout(10, TimeUnit.SECONDS)         //设置写入超时
                .cache(cache)   //设置缓存目录和100M缓存
                .addInterceptor(interceptor)    //添加日志拦截器（该方法也可以设置公共参数，头信息）
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)        //设置OkHttp
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //  添加数据解析ConverterFactory
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //添加RxJava
                .build();

        mApiService = retrofit.create(ApiService.class);
    }


    public static ApiService getApiService() {
        return mApiService;
    }


    public static RetrofitUtils getInstance() {
        return SingleLoader.INSTANCE;
    }

    private static class SingleLoader {
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    //    public static <S> S createService(Class<S> serviceClass) {
    //        return retrofit.create(serviceClass);
    //    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(App.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.e("no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(App.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}
