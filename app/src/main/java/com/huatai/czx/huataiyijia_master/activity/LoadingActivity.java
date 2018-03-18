package com.huatai.czx.huataiyijia_master.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.huatai.czx.huataiyijia_master.R;
import com.huatai.czx.huataiyijia_master.utils.CircleView;
import com.huatai.czx.huataiyijia_master.utils.DownLoadUtils;
import com.huatai.czx.huataiyijia_master.utils.ZipUtil;

import java.io.File;

public class LoadingActivity extends Activity {

    private CircleView circleview;
    String url = "http://121.41.101.209//dandiansit/SubSystem/ZY_W_F_0_53_eanalysis_sit_0_53.zip";
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 1){
                System.out.println("下载成功");
                String filename = Environment.getExternalStorageDirectory()+"/download/ZY_W_F_0_53_eanalysis_sit_0_53.zip";
                System.out.println("-----"+filename);
                File file = new File(filename);
                System.out.println("======"+file.getAbsolutePath());
                String dirName = Environment.getExternalStorageDirectory() + "/huatai_zip/";
                File f = new File(dirName);
                //不存在创建
                if (!f.exists()) {
                    f.mkdir();
                }
                ZipUtil.getInstance().unZip(f.getAbsolutePath(),file.getAbsolutePath());

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        circleview = findViewById(R.id.circleview);
        new Thread(new Runnable() {
            @Override
            public void run() {
                DownLoadUtils.get().download(url, "download", new DownLoadUtils.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                        Message message = new Message();
                        message.arg1 = 1;
                        handler.sendMessage(message);
                    }
                    @Override
                    public void onDownloading(int progress) {
                        System.out.println("-----------------"+progress);
                    }
                    @Override
                    public void onDownloadFailed() {
                        Message message = new Message();
                        message.arg1 = 2;
                        handler.sendMessage(message);
                    }
                });
            }
        }).start();
        //进度条从0到100
        circleview.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setDuration(4000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) animation.getAnimatedValue();
                circleview.setmCurrent((int) current);
                if((int)current == 100){
                    Toast.makeText(LoadingActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                      Intent intent8=new Intent(LoadingActivity.this, TestActivity.class);
                      startActivity(intent8);
                      finish();
                      circleview.setVisibility(View.GONE);
                }
            }
        });
        animator.start();
    }
}
