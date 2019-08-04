package com.horizon.app.horizonapp;

import android.app.Application;
import com.horizon.app.core.app.Horizon;

//全局初始化使用，数据可以在所有应用的activity上使用
public class HorizonApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        标准初始化模式
        Horizon.init(this)
                .withLoaderDelayed(1000)
                .configure();

    }
}
