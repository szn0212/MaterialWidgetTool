package com.study.library.network.app;

import android.content.Context;
import android.text.TextUtils;

import com.study.library.network.library.OkHttpUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 *
 *      网络框架：OkGo、 retrofit+rxJava
 */
public class RetrofitHelper {

    private static Retrofit getRetrofit(Context context,String baseUrl){
        if(TextUtils.isEmpty(baseUrl)){
            baseUrl = Api.HTTP_ADDRESS_BASE;
        }
        OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient(context);
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        return retrofit;
    }

    public static <T> T getService(Context context, Class<T> service){
        return getService(context, "", service);
    }

    public static <T> T getService(Context context, String baseUrl, Class<T> service){
        Retrofit retrofit = getRetrofit(context, baseUrl);
        return retrofit.create(service);
    }

}
