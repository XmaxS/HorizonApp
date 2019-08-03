package com.horizon.app.horizonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.horizon.app.core.ui.launcher.LauncherHolderCreator;
import com.horizon.app.core.ui.launcher.LauncherTag;
import com.horizon.app.core.util.storage.HorizonPreference;

import java.util.ArrayList;

public class ADActivity extends AppCompatActivity implements OnItemClickListener {


//  Banner控制器
    private ConvenientBanner<Integer> mConvenientBanner = null;

//    图片容器
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
//  判断是否启动滑动开始页面
        if (!HorizonPreference.getAppFlag(LauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            mConvenientBanner = findViewById(R.id.convenientBanner);
            initBanner();
        }else{
            activityStart();
        }

    }


    @Override
    public void onItemClick(int position) {
//      如果点击的是最后一个
        if (position == INTEGERS.size() - 1){
            HorizonPreference.setAppFlag(LauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            activityStart();
        }

    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
//                下面的圆球球，就是看当前是第几张图片的那一排小标志
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    private void activityStart(){
        startActivity(new Intent(ADActivity.this,ADTActivity.class));
        finish();
    }
}
