package com.huatai.czx.huataiyijia_master.view;

import com.huatai.czx.huataiyijia_master.bean.LoginBean;
import com.huatai.czx.huataiyijia_master.bean.UpdatePwBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2017/11/14.
 */

public interface InterfaceService {
  //用户登录
  @POST("nfeu/epad/login")
  @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("userCode") String userCode,@Field("passWord") String passWord);
  //修改密码
  @POST("nfeu/index/changePwd")
  @FormUrlEncoded
  Flowable<UpdatePwBean> getupdatePw(@FieldMap Map<String,String> map);
}
