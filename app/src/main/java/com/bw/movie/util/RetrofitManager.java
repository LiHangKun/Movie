package com.bw.movie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static final String BASE_URL = "http://mobile.bwstudent.com/movieApi/";

    private Apis mApis;

    private RetrofitManager() {
        initRetrofit();
    }

    private static class RetrofitManagerHolder {
        private final static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new GuDing())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApis = retrofit.create(Apis.class);
    }
    public boolean hasNet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }
    public Apis getApis(){
        return mApis;
    }

    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            String userId = SPUtils.getString(App.getAppContext(), SPUtils.USERINFO_NAME,
                    SPUtils.USERINFO_KEY_USER_ID);

            String sessionId = SPUtils.getString(App.getAppContext(), SPUtils.USERINFO_NAME,
                    SPUtils.USERINFO_KEY_SESSION_ID);

            if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(sessionId)) {
                return chain.proceed(request);
            }

            Request requestNew = request.newBuilder()
                    .addHeader("userId", userId)
                    .addHeader("sessionId", sessionId)
                    .build();
            return chain.proceed(requestNew);
        }
    }
    public class GuDing implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request ak = request.newBuilder()
                    .addHeader("ak", "0110010010000")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            return chain.proceed(ak);
        }
    }
    public MultipartBody getMultipartBody(Map<String,String> map){
        // 创建一个builder
        MultipartBody.Builder builder = new MultipartBody.Builder();
        // 遍历 map 集合
        for (Map.Entry<String,String> entry : map.entrySet()){
            // 转成文件
            File file = new File(entry.getValue());
            //"image" 接口中提供的文件参数的 key
            // entry.getKey() 以本地文件名作为传给服务器的文件名
            builder.addFormDataPart("image", entry.getKey(),
                    RequestBody.create(MediaType.parse("image/jpg"), file));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
    //xiangji
    public RequestBody getRequsetBody(List<File> files, HashMap<String,String> map){
//        if (map.size() < 1){
//            return null;
//        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String,String> entry:map.entrySet()){
            Log.i("xxx","key = "+entry.getKey()+"value = "+entry.getValue());
            builder.addFormDataPart(entry.getKey(),entry.getValue()+"");
        }

        for (int i = 0; i <files.size(); i++){
            builder.addFormDataPart("image",files.get(i).getName(),RequestBody.create(MediaType.parse("image/jepg"),files.get(i)));
        }

        return builder.build();
    }

}
