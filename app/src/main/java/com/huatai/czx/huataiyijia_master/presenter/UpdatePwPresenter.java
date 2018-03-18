package com.huatai.czx.huataiyijia_master.presenter;

import com.huatai.czx.huataiyijia_master.base.BasePresenter;
import com.huatai.czx.huataiyijia_master.bean.UpdatePwBean;
import com.huatai.czx.huataiyijia_master.model.UpdatePwModel;
import com.huatai.czx.huataiyijia_master.view.UpdatePwView;

/**
 * Created by lenovo on 2018/3/16.
 */

public class UpdatePwPresenter extends BasePresenter<UpdatePwView> implements UpdatePwModel.UpdatePwInterface {
    private UpdatePwModel updatePwModel;
    private UpdatePwView updatePwView;
    public UpdatePwPresenter(UpdatePwView mView) {
        super(mView);
        updatePwView=mView;
        updatePwModel=new UpdatePwModel();
        updatePwModel.setUpdatePwInterface(this);
    }
     public void setUpdatePw(String userCode,String passWord,String newPassWord){
         updatePwModel.getupdatepw(userCode,passWord,newPassWord);
     }
    @Override
    public void UpdatePwInterfaceSucces(UpdatePwBean bean) {
        updatePwView.UpdatePwInterfaceSucces(bean);
    }

    @Override
    public void UpdatePwInterfaceError(String msg) {
        updatePwView.UpdatePwInterfaceError(msg);
    }
}
