package com.github.xu.gank.service;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by xukankan on 2017/9/25.
 */

public class OkHttpFactory {
    private static int DEFAULT_CONNECT_TIMEOUT=5;//连接超时时间
    private static int DEFAULT_WRITE_TIMEOUT=20;//写入时间
    private static int DEFAULT_READ_TIMEOUT=10;//读取时间
    public static OkHttpClient getDedalutOkHttpFactory() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存

        //设置拦截器

        return httpClientBuilder.build();
    }
}
