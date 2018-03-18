package com.huatai.czx.huataiyijia_master.constant;

import android.util.Log;

/**
 * 日志工具类
 * 通过Constants.DEBUG是否打印
 */
public class CDLog {

    private static final String TAG = "info";

    public static void so(String tag, String info) {
        Log.e(tag, info);
    }

    public static void d(String info, Throwable ex) {
        if (Constants.DEBUG)
            Log.d(TAG, info, ex);
    }

    public static void debug(String tag, String info) {
        if (Constants.DEBUG)
            Log.d(tag, info);
    }

    public static void debug(Object info) {
        if (Constants.DEBUG)
            Log.d(TAG, (String) info);
    }

    public static void e(Object info) {
        if (Constants.DEBUG)
            Log.e(TAG, (String) info);
    }

    public static void p(Object info) {
        if (Constants.DEBUG)
            Log.e(TAG, (String) info);
    }

    public static void var(Object... objs) {
        if (objs != null) {
            String str = "";
            int len = objs.length;
            for (int i = 0; i < len; i++) {
                str += ",:" + objs[i].getClass().getSimpleName() + objs[i].toString() + "\n";
            }
            CDLog.e(str);
        }
    }
}
