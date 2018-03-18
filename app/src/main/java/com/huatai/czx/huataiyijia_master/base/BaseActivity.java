package com.huatai.czx.huataiyijia_master.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lenovo on 2017/10/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isstatus=false;//沉浸式状态栏（是否支持透明）
    private boolean isshowAction=false;//actionbar是否显示
    private boolean isFullscree=false;//是否支持全屏

    public abstract int bindLayout();
    public abstract void setLister();
    public abstract void Click(View view);
    public abstract void initView();
    public abstract void initDate();
    public abstract List<BasePresenter> initPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        setLister();
        initDate();
    }
    /**
     * 设置透明状态：沉浸式
     * @param status
     */
    public void setStatus(boolean status)
    {
        isstatus=status;
        if(isstatus)
        {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
            {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 设置actionbar是否显示
     * @param showActionBar
     */
    public void setshowActionBar(boolean showActionBar)
    {
        isshowAction=showActionBar;
        if(isshowAction)
        {
            getSupportActionBar().show();
        }
        else
        {
            getSupportActionBar().hide();

        }
    }



    /**
     * 设置全屏
     * @param fullScreen
     */
    public void setFullscree(boolean fullScreen)
    {
        isFullscree=fullScreen;
        if(isFullscree)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 显示点击
     *
     */
    @Override
    public void onClick(View view) {
        Click(view);
    }

    /**
     * 显示吐司
     * @param msg
     */
    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示无参
     *
     */
    public void startActivity(Class<?> clz)
    {
        Intent intent=new Intent(this,clz);
        startActivity(intent);
    }
    /**
     * 显示有参
     * @param
     */
    public void startActivity(Class<?> clz,Bundle bundle)
    {
        Intent intent=new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        List<BasePresenter> presenterList = initPresenter();
        if(presenterList!=null)
        {
            for (BasePresenter basePresenter : presenterList) {
                basePresenter.deacth();
            }
        }
    }
}
