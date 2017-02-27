package com.study.library.network.library;

import android.content.Context;
import android.util.Log;

import com.study.library.R;
import com.study.library.network.app.Config;
import com.study.library.network.cookie.JavaNetCookieJar;
import com.study.library.utils.NetworkUtils;
import com.study.library.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 */
public class OkHttpUtils {

    private static final String TAG = OkHttpUtils.class.getSimpleName();
    public static final int TIMEOUT = 10;
    private static OkHttpClient mOkHttpClient;


    public static void setOkHttpClient(OkHttpClient okHttpClient){
        mOkHttpClient = okHttpClient;
    }

    public static OkHttpClient getOkHttpClient(Context context){
        if(mOkHttpClient == null){
            init(context, null);
        }
        return mOkHttpClient;
    }

    private static void init(Context context, HashMap<String, String> commonRequestParams) {
        if(mOkHttpClient == null){
            BasicParmsInterceptor basicParamsInterceptor = null;
            if(commonRequestParams != null){
                basicParamsInterceptor = new BasicParmsInterceptor.Builder()
                        .addParamsMap(commonRequestParams).build();
            }

            //设置缓存路径
            File httpCacheFile = new File(context.getExternalCacheDir() + File.separator
                    + context.getString(R.string.app_name) + "Cache");
            //设置缓存最大占用10M
            Cache cache = new Cache(httpCacheFile, Config.HTTP_CACHE_SIZE);
            Log.i(TAG, "---http cache file path" + httpCacheFile.getAbsolutePath());

            if(basicParamsInterceptor == null){
                basicParamsInterceptor = new BasicParmsInterceptor.Builder().build();
            }

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    String name = Thread.currentThread().getName();

                    Log.i(TAG, "---thread name" + name);
                }
            });

            mOkHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(basicParamsInterceptor)
                    .addInterceptor(interceptor)
                    .cookieJar(new JavaNetCookieJar(context))
                    .cache(cache)
                    .build();
        }
    }

    public static Interceptor getNetworkInterceptor(final Context context){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if(NetworkUtils.isAvailable(context)){
                    int maxAge = 60;
                    // 有网络时 设置缓存超时时间60s-1分
                    //有网的时候读接口上的@Headers里的配置，可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    if(StringUtils.isEmpty(cacheControl)){
                        cacheControl = "public, max-age=" + maxAge;
                    }
                    response.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                }else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }

    public static Interceptor getInterceptor(final Context context){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isAvailable(context)) {
                    //没有网络，从本地缓存中读取
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }
}
