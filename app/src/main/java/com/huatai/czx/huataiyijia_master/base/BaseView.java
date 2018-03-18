package com.huatai.czx.huataiyijia_master.base;

/**
 * Created by lenovo on 2017/11/13.
 */
public interface BaseView<T> {
    void success(T t);
    void error(String msg);
    void failure(String msg);

}
