package com.horizon.app.core.app;

public enum ConfigKeys {
    API_HOST,                   //网络请求域名
    APPLICATION_CONTEXT,        //全局上下文
    CONFIG_READY,               //判断配置初始化是否完成
    LOADER_DELAYED,             //加载转圈圈
    INTERCEPTOR,                //拦截器
    HANDLER,                    //封装了消息投递、消息处理等接口
    ACTIVITY                    //用于活动判断
}
