package com.huatai.czx.huataiyijia_master.interceptor;

/**
 * Created by lenovo on 2017/12/6.
 */

import android.util.Log;

import com.huatai.czx.huataiyijia_master.application.App;
import com.huatai.czx.huataiyijia_master.utils.SpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor2 implements Interceptor {
    public String TAG = "LogInterceptor";
    public static String uid;
    public static String token;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //String  token = MyApp.context.getSharedPreferences("TOKEN",Context.MODE_PRIVATE).getString("token","A0C48974F2BBBA4EC3053CDE21328D70");
        SpUtils utils=new SpUtils(App.context,"Login");
        token = utils.getString("token", "");
        uid = utils.getString("uid", "");
        String method=request.method();
        if("POST".equals(method)){
            Log.d(TAG,"----------Start----------------");
            FormBody.Builder sb = new FormBody.Builder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.add(body.encodedName(i) , body.encodedValue(i));
                }
                body=sb.add("source","android").add("appVersion","101")
                        .add("token",token)
                        .build();
                request=request.newBuilder().post(body).build();
                Log.d(TAG, "| "+request.toString());
            }else {
                MultipartBody body=(MultipartBody)request.body();
                MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                build  .addFormDataPart("source","android");
                build  .addFormDataPart("appVersion","101");
                build  .addFormDataPart("token",token);
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    build.addPart(part);
                }
                request =request.newBuilder().post(build.build()).build();

            }
        }

      /*   String content = proceed.body().string();
             long duration=endTime-startTime;
            Log.d(TAG, "| Response:" + content);
            Log.d(TAG,"----------End:"+duration+"毫秒----------");*/
        return chain.proceed(request);
    }
}
