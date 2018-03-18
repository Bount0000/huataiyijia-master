package com.huatai.czx.huataiyijia_master;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatai.czx.huataiyijia_master.activity.ShowActivity;
import com.huatai.czx.huataiyijia_master.base.BaseActivity;
import com.huatai.czx.huataiyijia_master.base.BasePresenter;
import com.huatai.czx.huataiyijia_master.bean.LoginBean;
import com.huatai.czx.huataiyijia_master.presenter.LoginPresenter;
import com.huatai.czx.huataiyijia_master.utils.Base64Util;
import com.huatai.czx.huataiyijia_master.view.LoginView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements LoginView {

    private EditText username;
    private EditText userpwd;
    private Button login;
    private LoginPresenter loginPresenter;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setLister() {
        login.setOnClickListener(this);
    }

    @Override
    public void Click(View view) {
    switch (view.getId()){
        case R.id.login:
            String pw = userpwd.getText().toString();
           // String decode = Base64Util.decode(s);
            loginPresenter.getLoginPre(username.getText().toString(),Base64Util.encode(pw));
            break;
    }
    }

    @Override
    public void initView() {
        username =findViewById(R.id.username);
        userpwd = findViewById(R.id.userpwd);
        login = findViewById(R.id.login);
    }

    @Override
    public void initDate() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public List<BasePresenter> initPresenter() {
        List<BasePresenter> presenterList=new ArrayList<>();
        presenterList.add(loginPresenter);
        return presenterList;
    }

    @Override
    public void LoginInterfaceSucces(LoginBean bean) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginInterfaceError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginInterfaceOnFair(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
