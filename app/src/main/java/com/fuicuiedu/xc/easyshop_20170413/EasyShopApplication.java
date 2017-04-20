package com.fuicuiedu.xc.easyshop_20170413;

import android.app.Application;

import com.fuicuiedu.xc.easyshop_20170413.model.CachePreferences;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class EasyShopApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化本地配置
        CachePreferences.init(this);

        //########### ImageLoader相关初始化  #############
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)//开启硬盘缓存
                .cacheInMemory(true)//开启内存缓存
                .resetViewBeforeLoading(true)//加载前重置ImageView
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1024 * 1024 * 4)//设置内存缓存的大小，4M
                .defaultDisplayImageOptions(displayImageOptions)//默认加载选项
                .build();

        ImageLoader.getInstance().init(configuration);

    }
}
