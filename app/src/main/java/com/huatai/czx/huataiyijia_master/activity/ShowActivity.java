package com.huatai.czx.huataiyijia_master.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.huatai.czx.huataiyijia_master.R;
import com.huatai.czx.huataiyijia_master.base.BaseActivity;
import com.huatai.czx.huataiyijia_master.base.BasePresenter;
import com.huatai.czx.huataiyijia_master.utils.DialogUtils;

import java.util.List;

public class ShowActivity extends BaseActivity {


    private Button btn_ziying;
    private Button btn_fengxing;
    private ImageView shezhi;


    @Override
    public int bindLayout() {
        return R.layout.layout_show2;
    }

    @Override
    public void setLister() {
        btn_ziying.setOnClickListener(this);
        btn_fengxing.setOnClickListener(this);
        shezhi.setOnClickListener(this);
    }

    @Override
    public void Click(View view) {
    switch (view.getId()){
        case R.id.btn_ziying://自营
            DialogUtils.showMdDialog(this, "系统提示", "是否更新子系统", "取消", "确定", new DialogUtils.OnClickConfirmListener() {
                @Override
                public void onConfirm() {
                    startActivity(LoadingActivity.class);
                }
                @Override
                public void onCancel() {

                }
            });
            break;
        case R.id.btn_fengxing://蜂行
            startActivity(LoadingActivity.class);
            break;
        case R.id.shezhi:
            startActivity(SettingActivity.class);
            break;
    }
    }

    @Override
    public void initView() {
        btn_ziying = findViewById(R.id.btn_ziying);
        btn_fengxing = findViewById(R.id.btn_fengxing);
        shezhi = findViewById(R.id.shezhi);
    }

    @Override
    public void initDate() {

    }

    @Override
    public List<BasePresenter> initPresenter() {
        return null;
    }

}
