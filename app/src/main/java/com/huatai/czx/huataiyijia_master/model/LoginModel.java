package com.huatai.czx.huataiyijia_master.model;

import android.util.Log;

import com.google.gson.Gson;
import com.huatai.czx.huataiyijia_master.bean.LoginBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/16.
 */

public class LoginModel {
    public void getLogin(String userCode,String passWord ){
               /* new RetrofitUtils.Builder().addConverterFactory()
               .addCallAdapterFactory()
               .builder().getService().getLogin(userCode,passWord)
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<LoginBean>() {
                  @Override
                  public void onNext(LoginBean loginBean) {
                      if("0".equals(loginBean.code)){
                          loginInterface.LoginInterfaceSucces(loginBean);
                      } else if("1".equals(loginBean.code))
                      {
                          loginInterface.LoginInterfaceError(loginBean.msg);
                      }else
                      {
                          loginInterface.LoginInterfaceOnFair(loginBean.msg);
                      }
                  }
                  @Override
                  public void onError(Throwable t) {
                  }

                  @Override
                  public void onComplete() {

                  }
              });*/
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10,TimeUnit.MINUTES)
                .readTimeout(20, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("system","htall")
                                .addHeader("eqp","app")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        //post方式提交的数据
        FormBody formBody = new FormBody.Builder()
                //.add("transNo","0000002017021516390556179")
                .add("userCode", userCode)
                .add("passWord", passWord)
                .build();
        final Request request = new Request.Builder()
        //http://10.100.252.190:8080/nfeu/epad/login
                .url("http://www.baidu.com")//请求的url
                .post(formBody)
                .build();
        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Tag","______________");
                Log.i("Tag2",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code()==200) {
                    Log.i("Tag","1233"+response.body().string());
                    //System.out.println(response.body().string());
                    String result = response.body().string();
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                    if("0".equals(loginBean.code)){
                        loginInterface.LoginInterfaceSucces(loginBean);
                    }else if("1".equals(loginBean.code)){
                        loginInterface.LoginInterfaceError(loginBean.msg);
                    }else {
                        loginInterface.LoginInterfaceOnFair(loginBean.msg);
                    }
                }else {
                    Log.i("Tag","1233"+response.code());
                }
            }
        });

/*
        RequestBody requestBody=new FormBody.Builder().add("userCode",userCode).add("passWord",passWord).build();
              OkHttpClient client = new OkHttpClient();
           final Request request2 = new Request.Builder().url("http://10.100.252.190:8080/nfeu/epad/login").post(requestBody)
                 .addHeader("Content-Type","application/json")
                 .addHeader("system","eqp")
                 .build();
                 client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Tagerror",call.toString());

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("Tag2","======================");
                String result = response.body().string();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                if("0".equals(loginBean.code)){
                    loginInterface.LoginInterfaceSucces(loginBean);
                }else if("1".equals(loginBean.code)){
                    loginInterface.LoginInterfaceError(loginBean.msg);
                }else {
                    loginInterface.LoginInterfaceOnFair(loginBean.msg);
                }
            }
    });*/
}
    private  LoginInterface loginInterface ;

    public void setLoginInterface(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public  interface LoginInterface{
        void LoginInterfaceSucces(LoginBean bean);
        void LoginInterfaceError(String msg);
        void LoginInterfaceOnFair(String msg);


    }
}

