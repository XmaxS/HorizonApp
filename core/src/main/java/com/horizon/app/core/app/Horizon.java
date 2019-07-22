package com.horizon.app.core.app;

import android.content.Context;
import android.os.Handler;

//用于调用Configurator
public final class Horizon {
    public static Configurator init(Context context){
        Configurator.getInstance()
                .getHorizon_Configs()
                .put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler(){
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test(){
        //测试用
    }
}
