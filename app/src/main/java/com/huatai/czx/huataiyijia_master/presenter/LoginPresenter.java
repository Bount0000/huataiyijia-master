package com.huatai.czx.huataiyijia_master.presenter;

import com.huatai.czx.huataiyijia_master.base.BasePresenter;
import com.huatai.czx.huataiyijia_master.bean.LoginBean;
import com.huatai.czx.huataiyijia_master.model.LoginModel;
import com.huatai.czx.huataiyijia_master.view.LoginView;

/**
 * Created by lenovo on 2018/3/16.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.LoginInterface {
    private LoginModel loginModel;
    private LoginView loginView;
    public LoginPresenter(LoginView mView) {
        super(mView);
        loginView=mView;
        loginModel=new LoginModel();
        loginModel.setLoginInterface(this);
    }
   public void getLoginPre(String userCode,String passWord){
       loginModel.getLogin(userCode,passWord);
   }
    @Override
    public void LoginInterfaceSucces(LoginBean bean) {
        loginView.LoginInterfaceSucces(bean);
    }

    @Override
    public void LoginInterfaceError(String msg) {
        loginView.LoginInterfaceError(msg);
    }
    @Override
    public void LoginInterfaceOnFair(String msg) {
        loginView.LoginInterfaceOnFair(msg);
    }

}
