package com.huatai.czx.huataiyijia_master.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.huatai.czx.huataiyijia_master.MyApplication;
import com.huatai.czx.huataiyijia_master.R;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Created by Administrator on 2017/9/18.
 */

public class UIUtils {

    private static Toast toast = null;
    private static long oneTime;
    private static long twoTime;
    private static String oldMsg;

    /**
     * 判断当前的线程是不是在主线程
     *
     * @return
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == MyApplication.getMainThreadId();
    }

    /** 获取主线程的handler */
    public static Handler getHandler() {
        return MyApplication.getMainThreadHandler();
    }

    /** 延时在主线程执行runnable */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /** 在主线程执行runnable */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }


    public static Context getContext() {
        return MyApplication.getApplication();
    }

    /** 获取drawable */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 主线程运行Runnable
     *
     * @param runnable
     */
    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        } else {
            // 如果仅仅是用来判断网络连接　　　　　　
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                // Log.d(TAG, "isNetworkAvailable - 是否有网络： "+ networkInfo.isAvailable());
                return true;
            } else {
                // Log.d(TAG, "isNetworkAvailable - 完成没有网络！");
                return false;
            }

	          /*  // 1、判断是否有3G网络
	            if (networkInfo != null
	                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
	               // Log.d(TAG, "isNetworkAvailable - 有3G网络");
	                return true;
	            } else {
	               // Log.d(TAG, "isNetworkAvailable - 没有3G网络");
	            }

	            // 2、判断是否有wifi连接
	            if (networkInfo != null
	                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
	               // Log.d(TAG, "isNetworkAvailable - 有wifi连接");
	                return true;
	            } else {
	              //  Log.d(TAG, "isNetworkAvailable - 没有wifi连接");
	            } */
        }
    }

    /** dip转换px */
    public static int dip2px(double dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** pxz转换dip */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }
    public static View inflate(int resId, ListView dest) {
        return LayoutInflater.from(getContext()).inflate(resId, dest, false);
    }

    static InputMethodManager inputManager = null;
    /**
     * get inputmethod manager
     * @param context
     * @return {@link InputMethodManager}
     */
    public static InputMethodManager getInputManger(Context context) {
        if(inputManager == null) inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputManager;
    }

    /** 获取资源 */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /** 获取文字 */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 是否显示Toast
     * @param isToast
     */
    public static void setIsToast(boolean isToast) {
        UIUtils.isToast = isToast;
    }

    private static boolean isToast = true;


    /** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /** 对toast的简易封装。线程安全，可以在非UI线程调用。 */
    public static void showToastSafe(final String str) {
        if (isToast) {
            if (isRunInMainThread()) {
                showToast(str);
            } else {
                post(new Runnable() {
                    @Override
                    public void run() {
                        showToast(str);
                    }
                });
            }
        }
    }

    private static void showToast(String str) {
        if(toast == null){
            toast = Toast.makeText(MyApplication.getApplication(), str, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(str.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_LONG){
                    toast.show() ;
                }
            }else{
                oldMsg = str ;
                toast.setText(str) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }

    public static void startActivity(Context context, Class clazz,
                                     boolean isFinish) {
        startActivity(context, clazz, isFinish, null);
        /*if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_menu_out);
        }*/
    }

    public static void startActivity(Class clazz, boolean isFinish) {
        startActivity(getContext(), clazz, isFinish, null);
        /*if (getContext() instanceof Activity) {
            ((Activity) getContext()).overridePendingTransition(
                    R.anim.push_left_in, R.anim.push_menu_out);
        }*/
    }

    public static void startActivity(Context context, Class clazz,
                                     boolean isFinish, Bundle bundle) {
        if (context == null || clazz == null) {
            return;
        }
        Intent intent = new Intent(context, clazz);
        if (!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
        /*if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(
                    R.anim.push_left_in, R.anim.push_menu_out);*/
        if (isFinish) {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }

    /**
     * 获取刷新展示View,并设置公共特性
     * @param mAct 上下文
     * @param ptrFrameLayout 刷新控件
     * @return 刷新View
    public static MaterialHeader getRefreshView(Activity mAct, PtrFrameLayout ptrFrameLayout) {
        MaterialHeader refreshView = new MaterialHeader(mAct);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        refreshView.setColorSchemeColors(colors);
        refreshView.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        refreshView.setPadding(0, UIUtils.dip2px(15), 0, UIUtils.dip2px(15));
        refreshView.setPtrFrameLayout(ptrFrameLayout);
        //刷新时保留头部
        ptrFrameLayout.setKeepHeaderWhenRefresh(true);
        //解决viewPager滑动冲突
        ptrFrameLayout.disableWhenHorizontalMove(true);
        return refreshView;
    }

    *//**
     * 获取刷新展示View,并设置公共特性
     * @param mAct 上下文
     * @return 刷新View
     *//*
    public static MaterialHeader getMdRefreshView(Activity mAct, PtrFrameLayout ptrFrameLayout) {
        MaterialHeader refreshView = new MaterialHeader(mAct);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        refreshView.setColorSchemeColors(colors);
        refreshView.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        refreshView.setPadding(0, UIUtils.dip2px(15), 0, UIUtils.dip2px(15));
        refreshView.setPtrFrameLayout(ptrFrameLayout);
        return refreshView;
    }*/

    public static int getScreenWidth(Activity mAct) {
        DisplayMetrics metrics = new DisplayMetrics();
        mAct.getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity mAct) {
        DisplayMetrics metrics = new DisplayMetrics();
        mAct.getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static void call() {
        call(null);
    }

    /**
     * 拨打电话
     * @param num
     */
    public static void call(String num) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + num));
        startActivity(intent);
    }

    public static void startActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

    /**
     * Get string from a second time with patten "yyyy-MM-dd HH:mm"
     * @param created_at	second
     * @return yyyy-MM-dd HH:mm
     */
    public static String getTime(long created_at) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(new Date(created_at * 1000));
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 无状态栏
     */
    public static final int nullStatusBar = 102;
    /**
     * 状态栏加颜色
     */
    public static final int colorStatusBar = 103;

    /**
     * 设置状态栏样式
     * @param style
     */
    public static void setStatusBarStyle(Activity activity, int style) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        int statusBarHeight = UIUtils.getStatusBarHeight(activity);
        switch (style) {
            case nullStatusBar:
                if (mChildView != null) {
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                }
                //需要设置这个 flag 才能设置状态栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //避免多次调用该方法时,多次移除了 View
                if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
                    //移除假的 View.
                    mContentView.removeView(mChildView);
                    mChildView = mContentView.getChildAt(0);
                }
                if (mChildView != null) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                    //清除 ChildView 的 marginTop 属性
                    if (lp != null && lp.topMargin >= statusBarHeight) {
                        lp.topMargin -= statusBarHeight;
                        mChildView.setLayoutParams(lp);
                    }
                }
                break;
            case colorStatusBar:
                //First translucent status bar.
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (mChildView != null) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                    //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                    if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                        //不预留系统空间
                        ViewCompat.setFitsSystemWindows(mChildView, false);
                        lp.topMargin += statusBarHeight;
                        mChildView.setLayoutParams(lp);
                    }
                }
                View statusBarView = mContentView.getChildAt(0);
                if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                    //避免重复调用时多次添加 View
                    statusBarView.setBackgroundColor(UIUtils.getColor(R.color.blue1e));
                    return;
                }
                statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
                statusBarView.setBackgroundColor(UIUtils.getColor(R.color.blue1e));
                //向 ContentView 中添加假 View
                mContentView.addView(statusBarView, 0, lp);
                break;
        }
    }

    /**
     * 设置状态栏样式
     * @param style
     */
    public static void setStatusBarStyle(Activity activity, int style, int color) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        int statusBarHeight = UIUtils.getStatusBarHeight(activity);
        switch (style) {
            case nullStatusBar:
                if (mChildView != null) {
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                }
                //需要设置这个 flag 才能设置状态栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //避免多次调用该方法时,多次移除了 View
                if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
                    //移除假的 View.
                    mContentView.removeView(mChildView);
                    mChildView = mContentView.getChildAt(0);
                }
                if (mChildView != null) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                    //清除 ChildView 的 marginTop 属性
                    if (lp != null && lp.topMargin >= statusBarHeight) {
                        lp.topMargin -= statusBarHeight;
                        mChildView.setLayoutParams(lp);
                    }
                }
                break;
            case colorStatusBar:
                //First translucent status bar.
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (mChildView != null) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                    //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                    if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                        //不预留系统空间
                        ViewCompat.setFitsSystemWindows(mChildView, false);
                        lp.topMargin += statusBarHeight;
                        mChildView.setLayoutParams(lp);
                    }
                }
                View statusBarView = mContentView.getChildAt(0);
                if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                    //避免重复调用时多次添加 View
                    statusBarView.setBackgroundColor(UIUtils.getColor(color));
                    return;
                }
                statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
                statusBarView.setBackgroundColor(UIUtils.getColor(color));
                //向 ContentView 中添加假 View
                mContentView.addView(statusBarView, 0, lp);
                break;
        }
    }

    /**
     * 自定义文字颜色
     */
    public static Spannable describeTextColor(String oldMsg, int cutLength, int inSize, int outSize, int ... color) {
        int length = oldMsg.length();
        Spannable spannableString = new SpannableString(oldMsg);
        spannableString.setSpan(new AbsoluteSizeSpan(UIUtils.dip2px(inSize)), 0, cutLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(UIUtils.getColor(color[0])), 0, cutLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(UIUtils.dip2px(outSize)), cutLength, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(UIUtils.getColor(color[1])), cutLength, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
