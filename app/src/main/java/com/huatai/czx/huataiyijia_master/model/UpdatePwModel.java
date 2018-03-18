package com.huatai.czx.huataiyijia_master.model;

import com.huatai.czx.huataiyijia_master.bean.UpdatePwBean;
import com.huatai.czx.huataiyijia_master.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by lenovo on 2018/3/16.
 */

public class UpdatePwModel {

    public void getupdatepw(String userCode,String passWord,String newPassWord){
        Map<String,String> map=new HashMap<>();
        map.put("userCode",userCode);
        map.put("passWord",passWord);
        map.put("newPassWord",newPassWord);
     new RetrofitUtils.Builder().addConverterFactory()
             .addCallAdapterFactory()
             .builder().getService().getupdatePw(map)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new DisposableSubscriber<UpdatePwBean>() {
                 @Override
                 public void onNext(UpdatePwBean bean) {
                     if("0".equals(bean.code)){
                         updatePwInterface.UpdatePwInterfaceSucces(bean);
                     } else if("1".equals(bean.code))
                     {
                         updatePwInterface.UpdatePwInterfaceError(bean.msg);

                     }
                 }

                 @Override
                 public void onError(Throwable t) {

                 }

                 @Override
                 public void onComplete() {

                 }
             });
    }
    public UpdatePwInterface updatePwInterface;

    public void setUpdatePwInterface(UpdatePwInterface updatePwInterface) {
        this.updatePwInterface = updatePwInterface;
    }

    public   interface  UpdatePwInterface{
        void UpdatePwInterfaceSucces(UpdatePwBean bean);
        void UpdatePwInterfaceError(String msg);
    }
}
