package com.qixiao.bm.network;

import android.util.Log;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BMContants;
import com.qixiao.bm.Utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public final static int TIME_OUT = 20;
    public final static String CONTENT_TYPR = "Content-Type";
    public static  final  String BASE_URL="http://api.androidhive.info/";
    private   static RetrofitService retrofitService = null;

    public  static RetrofitService getService(){
            if (retrofitService == null){
                synchronized (RetrofitManager.class){
                    if (retrofitService==null){
                            retrofitService = getRetrofit().create(RetrofitService.class);
                    }
                }
            }
            return retrofitService;
    }
    private static Interceptor  headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader(CONTENT_TYPR,"application/json");

            String token = new SharedPreferencesUtils(BMApplication.getContext()).getString(BMContants.TOKEN);
            builder.addHeader(BMContants.TOKEN,token);

            Log.d("RetrofitManager",token);
            Request request = builder.build();
            return chain.proceed(request);
        }
    };

    private static  Retrofit getRetrofit(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
               // .addInterceptor(headerInterceptor)
                //.addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        return  retrofit;
    }

}
