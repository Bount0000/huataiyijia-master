package com.huatai.czx.huataiyijia_master.utils;

import android.os.Environment;

import com.huatai.czx.huataiyijia_master.interceptor.CachingControlInterceptor;
import com.huatai.czx.huataiyijia_master.view.InterfaceService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.huatai.czx.huataiyijia_master.Api.Api.Api_Url;


/**
 * Created by lenovo on 2017/11/27.
 */

public class RetrofitUtils {
    private static final long cacheSize=1024*1024*20;
    private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okttpcaches"; // 设置缓存文件路径
    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);  //
    public InterfaceService service;
    public static SpUtils.RetrofitUtils retrofitUtils;
    public RetrofitUtils(InterfaceService service)
    {
        this.service=service;
    }
    public InterfaceService getService()
    {
        return service;
    }
    public static class Builder
    {
        OkHttpClient okbuilder = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8,TimeUnit.SECONDS)
                .readTimeout(8,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                //有网络时的拦截器
                .addNetworkInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                //没网络时的拦截器
                .addInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .cache(cache)
                //.addInterceptor(new MyInterceptor()).build();
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("eqp ","app")
                                .addHeader("system","htall")
                                .build();

                        return chain.proceed(request);
                    }
                })
        .build();


        Retrofit.Builder builder=new Retrofit.Builder().client(okbuilder).baseUrl(Api_Url);
        private InterfaceService service;
        public Builder addCallAdapterFactory()
        {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            return this;
        }
        public Builder addConverterFactory()
        {
            builder.addConverterFactory(GsonConverterFactory.create());
            return this;
        }
        public SpUtils.RetrofitUtils builder()
        {
            service = builder.build().create(InterfaceService.class);
            retrofitUtils= new SpUtils.RetrofitUtils(service);
            return retrofitUtils;
        }
    }
}
