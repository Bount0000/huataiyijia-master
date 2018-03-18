package com.huatai.czx.huataiyijia_master.constant;

import android.Manifest;
import android.os.Environment;

/**
 * wsy
 * 瓶子
 */
public class BottleConstant {

   /* *//**
     * 当前包版本
     *//*
    public static final boolean is = BuildConfig.ENV_TYPE != EnvType.PRODUCT;

    *//**
     * 环境
     *//*
    public static final String BASE_URL = BottleConstant.is ? BottleConstant.BASE_URL_DEVELOP : BottleConstant.BASE_URL_PRODUCT;
    public static final String BASE_URL_RX = BottleConstant.is ? BottleConstant.BASE_URL_RX_DEVELOP : BottleConstant.BASE_URL_RX_PRODUCT;
    public static final String BASE_URL_H5_HOST = BottleConstant.is ? BottleConstant.BASE_URL_H5_HOST_DEVELOP : BottleConstant.BASE_URL_H5_HOST_PRODUCT;
    public static final String VERIFY_CODE = BottleConstant.is ? BottleConstant.VERIFY_CODE_DEVELOP : BottleConstant.VERIFY_CODE_PRODUCT;
    public static final String JPUSH_KEY = BottleConstant.is ? BottleConstant.JPUSH_DEVELOP : BottleConstant.JPUSH_PRODUCT;
    public static final String UMENG_KEY = BottleConstant.is ? BottleConstant.UMENG_DEVELOP : BottleConstant.UMENG_PRODUCT;*/

    /**
     * 测试环境
     */
    private static final String BASE_URL_DEVELOP = "http://47.104.98.100:8080";  // 测试环境
    private static final String BASE_URL_RX_DEVELOP = "http://47.104.98.100:8080";  // 测试环境
    private static final String BASE_URL_H5_HOST_DEVELOP = "http://h5.linkedshow.com:81";  // 测试环境
    private static final String VERIFY_CODE_DEVELOP = "3fdcbb9a93b65ac69eab2fcd0f0b48d1"; //测试短信
    private static final String JPUSH_DEVELOP = "3a3bb30155bc2483a52b5e18"; //JPUSH测试key
    private static final String UMENG_DEVELOP = "59cb39dd1c5dd032ef00000c"; //UMENG测试key

    /**
     * 生产环境
     */
    private static final String BASE_URL_PRODUCT = "http://47.104.98.100:8080";  // 正式环境
    private static final String BASE_URL_RX_PRODUCT = "http://47.104.98.100:8080";  // 正式环境
//    private static final String BASE_URL_RX_PRODUCT = "http://192.168.1.117:80";  // 正式环境
    private static final String BASE_URL_H5_HOST_PRODUCT = "http://h5.linkedshow.com:82";  // 正式环境
    private static final String VERIFY_CODE_PRODUCT = "63a34abbb5f5509788422883b5d402e1";  //正式短信
    private static final String JPUSH_PRODUCT = "1853a8e3ab8cbf5c23e6ce12"; //JPUSH正式key
    private static final String UMENG_PRODUCT = "59cb3a0f5312dd01bf00001c"; //UMENG正式key

    //微信
    public static final String WEIXIN_APP_ID = "wx1b451c9e9bbb4fb3"; // 微信开发者APP_ID
    public static final String WEIXIN_APP_SECRET = "392c213ffd8d177c1ae16f55e2c898b6";
    //微博
    public static final String WEIBO_APP_ID = "817270931"; // 微博APP_ID
    public static final String WEIBO_APP_SECRET = "3167923b317c53cfd582a7b3d809d2a6";
    //小米推送
    public static final String XiaoMi_PUSH_APP_ID = "2882303761517682056";
    public static final String XiaoMi_PUSH_APP_KEY = "5731768277056";
    public static final String XiaoMiPush = "XiaoMiPush";
    //ping++APP_ID
    public static final String PingXX_APP_ID = "app_Oe9GmDHCGSiPGyLa";

    public static final String BASE_SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constants.packageName;
    public static final String DATA_CACHE_DIR = BASE_SDCARD_DIR + "/cache";
    public static final String PRIVATE_CACHE_DIR = BASE_SDCARD_DIR + "/data/avatar/";

    public static final String ACTION_LOGIN = "ACTION_LOGIN";
    public static final String ACTION_LOGOUT = "ACTION_LOGOUT";
    public static final String ACTION_HOME_TASK_STATE = "ACTION_HOME_TASK_STATE";

    /**
     * 地图搜索
     */
    public static final String MAP_SEARCH_KEY = "MAP_SEACH_KEY";
    public static final String MAP_SEARCH_OUTPATIENT = "门诊";
    public static final String MAP_SEARCH_HOSPITAL = "医院";
    /**
     * h5界面网址
     */
    public static final String HTML_URL = "HTML_URL";
    public static final String HAOWAN_APPID = "47475";
    public static final String HAOWAN_PRIVATE_TOKEN = "7v2prycubczyn40yypvu";

    /**
     * 聊天界面固定值
     */
    public static final String TARGETID = "targetId";
    public static final String TITLE = "title";
    public static final String HEADICON = "headIcon";

    /**
     * 疾病史和手术史回调信息
     */
    public static final String INFO = "INFO";


    /**
     * 医生详情
     */
    public static final String DOCTOR_ID = "DOCTOR_ID";


    /**
     * 本地用户信息
     */
    public static final String SP_USER_LOGIN = "SP_USER_LOGIN";

    /**
     * 本地常量信息
     */
    public static final String SP_ACCURATEMODULE_LOGIN = "SP_ACCURATEMODULE_LOGIN";

    /**
     * 推送新消息
     */
    public static final String ACTION_NEW_MESSAGE = "com.zhuhui.ai.action.new.message";

    /**
     * 摄像头权限
     */
    public static final String[] cameraPer = new String[]{
            Manifest.permission.CAMERA
    };

    /**
     * 手机设备信息权限
     */
    public static final String[] readPhonePer = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     * 定位权限
     */
    public static final String[] locationPer = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    /**
     * 蓝牙权限
     */
    public static final String[] bluetoothPer = new String[]{
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
    };

    /**
     * 读写权限
     */
    public static final String[] writeReadPer = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


}
