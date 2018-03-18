package com.huatai.czx.huataiyijia_master.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huatai.czx.huataiyijia_master.R;
import com.huatai.czx.huataiyijia_master.base.BaseActivity;
import com.huatai.czx.huataiyijia_master.base.BasePresenter;
import com.huatai.czx.huataiyijia_master.bean.UpdatePwBean;
import com.huatai.czx.huataiyijia_master.presenter.UpdatePwPresenter;
import com.huatai.czx.huataiyijia_master.view.UpdatePwView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity implements UpdatePwView {

    private UpdatePwPresenter pwPresenter;
    private EditText oldpw;
    private EditText newpw;
    private Button btn_ok;

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void setLister() {
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void Click(View view) {
     switch (view.getId()){
      case R.id.btn_ok:
          pwPresenter.setUpdatePw(null,oldpw.getText().toString(),newpw.getText().toString());
         break;
}
    }

    @Override
    public void initView() {
        oldpw = findViewById(R.id.oldpw);
        newpw = findViewById(R.id.newpw);
        btn_ok = findViewById(R.id.btn_ok);

    }

    @Override
    public void initDate() {
        pwPresenter = new UpdatePwPresenter(this);

    }

    @Override
    public List<BasePresenter> initPresenter() {
        List<BasePresenter> presenterList=new ArrayList<>();
        presenterList.add(pwPresenter);
        return presenterList;
    }

    @Override
    public void UpdatePwInterfaceSucces(UpdatePwBean bean) {

    }

    @Override
    public void UpdatePwInterfaceError(String msg) {

    }
}
