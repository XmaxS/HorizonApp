package com.horizon.app.horizonapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import com.horizon.app.core.app.Horizon;
import com.horizon.app.core.ui.loader.HorizonLoader;

public class ADTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏，这个和下面的隐藏标题栏必须放在setContentView之前执行
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏
        getSupportActionBar().hide();

        setContentView(R.layout.activity_adt);

        //2秒后转到map主活动界面，此界面销毁
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ADTActivity.this,MapActivity.class));
                finish();
            }
        },2000);
    }
}
