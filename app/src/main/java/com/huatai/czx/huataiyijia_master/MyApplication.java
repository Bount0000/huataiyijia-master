package com.huatai.czx.huataiyijia_master;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import cn.jpush.android.api.JPushInterface;
import io.realm.Realm;

/**
 * Application
 * Created by czx on 2018/2/12.
 */
public class MyApplication extends Application {
    /**
     * 全局Context
     */
    private static MyApplication mInstance;
    /**
     * 主线程ID
     */

    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;
    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        //Realm数据库初始化
        Realm.init(this);
        //消息推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
    /**
     * 获取当前应用主入口实例
     * @return
     */
    public static MyApplication getApplication() {
        return mInstance;
    }
    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }
    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }


}
