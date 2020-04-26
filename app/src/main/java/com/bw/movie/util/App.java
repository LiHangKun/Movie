package com.bw.movie.util;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.previewlibrary.ZoomMediaLoader;

/**
 * 104.创建一个app类，继承Application
 * 105.写一个静态成员变量，记录上下文
 * 106.在onCreate中通过getApplicationContext来给成员变量赋值
 * 107.编写一个静态方法，提供外部调用获取上下文
 * 108.在我们的manifest里面，application下，添加android:name=“该类的路径” ！！！！！
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig pipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryPath(Environment.getDownloadCacheDirectory())//设置外部SD卡缓存目录文件
                        .setBaseDirectoryName("cc/")//设置缓存名称
                        .setMaxCacheSize(10*1024*1024)//设置缓存大小
                        .build())
                .build();
        //初始化
        Fresco.initialize(this, pipelineConfig);

        mContext = getApplicationContext();

    }

    public static Context getAppContext(){
        return mContext;
    }
}
