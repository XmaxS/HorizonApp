package com.horizon.app.core.util.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    public void sendRequsetWithOkHttp(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


}
