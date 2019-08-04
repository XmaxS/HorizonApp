package com.horizon.app.core.app;

import android.app.Activity;
import android.os.Handler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

//项目初始化配置
public final class Configurator {
    private static final HashMap<Object,Object> Horizon_Configs = new HashMap<>();
    private static final Handler HANDLER = new Handler();

    private Configurator(){
        Horizon_Configs.put(ConfigKeys.CONFIG_READY,false);
        Horizon_Configs.put(ConfigKeys.HANDLER,HANDLER);
    }

    //静态内部类，线程安全的单例懒汉模式
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getHorizon_Configs(){
        return Horizon_Configs;
    }

    //配置成功
    public final void configure(){
        Logger.addLogAdapter(new AndroidLogAdapter());
        Horizon_Configs.put(ConfigKeys.CONFIG_READY,true);
        //Utils.init(Horizon.getApplicationContext());
    }


    public final Configurator withLoaderDelayed(long delayed) {
        Horizon_Configs.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }



    //检查配置项是否完成,保证配置完成性和正确性
    private void checkConfiguration() {
        final boolean isReady = (boolean) Horizon_Configs.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = Horizon_Configs.get(key);
        if (value == null){
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) Horizon_Configs.get(key);
    }

    public final Configurator withActivity(Activity activity) {
        Horizon_Configs.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }


}
