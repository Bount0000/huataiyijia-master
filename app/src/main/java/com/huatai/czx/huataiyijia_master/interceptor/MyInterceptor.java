package com.huatai.czx.huataiyijia_master.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/11/27.
 */

public class MyInterceptor implements Interceptor {
    public static String uid;
    public static String token;
    public static  int versioncode;
    public static String icon;

    public Response intercept(Chain chain) throws IOException
    {
         /* SpUtils utils=new SpUtils(App.context,"Login");
          token = utils.getString("token", "");
          uid = utils.getString("uid", "");
         icon = utils.getString("icon", "");*/
    /*   PackageManager manager = App.context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(App.context.getPackageName(),0);
            versioncode = info.versionCode;
            System.out.println("==versioncode===="+versioncode);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Request request=chain.request();
        //判断当前的请求
        if (request.method().equals("POST")){
            //判断当前的请求Boby
            if (request.body() instanceof FormBody){
                //创建一个新的FromBoby
                FormBody.Builder bobyBuilder = new FormBody.Builder();
                //获取原先的boby
                FormBody body = (FormBody) request.body();
                //遍历boby
                for (int i = 0; i < body.size(); i++) {
                    //取出原先boby的数据  存入新的boby里
                    bobyBuilder.add(body.encodedName(i),body.encodedValue(i));
                }
                System.out.println("===token======"+token);
                body=bobyBuilder.addEncoded("ldToken",token)
                        .addEncoded("eqp","app")
                        .addEncoded("system","htall")
                        .build();
                request=request.newBuilder().post(body).build();
            }
            else
            {
                MultipartBody body= (MultipartBody) request.body();
                MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("ldToken",token);
                builder.addFormDataPart("eqp ","app");
                builder.addFormDataPart("system","htall");
                List<MultipartBody.Part> parts=body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request=request.newBuilder().post(builder.build()).build();
                Log.e("request==============:",request.body().toString());
            }
            //Response response = chain.proceed(request);q
            //Log.e("result==============:",response.body().string());
            }
            if(request.method().equals("GET"))
            { //添加公共参数
                HttpUrl httpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("ldToken ",token)
                        .addQueryParameter("eqp","app")
                        .addQueryParameter("system ","htall")
                        .build();
                request = request.newBuilder().url(httpUrl).build();
            }
         return chain.proceed(request);
    }
}
