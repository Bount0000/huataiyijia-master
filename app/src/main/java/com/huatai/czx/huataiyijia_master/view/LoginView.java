package com.huatai.czx.huataiyijia_master.view;

import com.huatai.czx.huataiyijia_master.bean.LoginBean;

/**
 * Created by lenovo on 2018/3/16.
 */

public interface LoginView {
    void LoginInterfaceSucces(LoginBean bean);
    void LoginInterfaceError(String msg);
    void LoginInterfaceOnFair(String msg);
}
