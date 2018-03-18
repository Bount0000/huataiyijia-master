package com.huatai.czx.huataiyijia_master.constant;

import android.os.Environment;


/**
 * @author eyan
 *         静态变量
 */
public class Constants {

    public static String packageName = "com.zhuhui.ai";

    public static final String APK_DOWNLOAD_URL = "url";

    public static final String APK_UPDATE_CONTENT = "updateMessage";

    public static final String BASE_SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + packageName;

    public static final String BASE_DATA_CACHE_DIR = BASE_SDCARD_DIR + "/cache/data";

    public static final String BASE_IMAGE_CACHE_DIR = BASE_SDCARD_DIR + "/cache/image";

    public static final String BASE_LOG_DIR = BASE_SDCARD_DIR + "/log";

    public static boolean DEBUG = true;
}
