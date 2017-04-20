package com.fuicuiedu.xc.easyshop_20170413.network;

import android.util.Log;

import com.fuicuiedu.xc.easyshop_20170413.model.CachePreferences;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 整个项目的网络模块客户端
 */

public class EasyShopClient {
    private static EasyShopClient easyShopClient;

    private OkHttpClient okHttpClient;
    private Gson gson;

    public static EasyShopClient getInstance(){
        if (easyShopClient == null){
            easyShopClient = new EasyShopClient();
        }
        return easyShopClient;
    }



    private EasyShopClient(){
        //初始化日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置拦截级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();

        gson = new Gson();
    }

    //登陆
    public Call login(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.LOGIN)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //注册
    public Call register(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.REGISTER)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //修改头像
    public Call uploadAvatar(File file){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //传一个用户类JSON字符串格式
                .addFormDataPart("user",gson.toJson(CachePreferences.getUser()))
                .addFormDataPart("image",file.getName(),
                        RequestBody.create(MediaType.parse("image/png"),file))
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.UPDATA)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
}
