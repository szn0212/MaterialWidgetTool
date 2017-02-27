package com.study.library.network.library;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 *
 *
 * RequestBody的数据格式都要指定Content-Type，常见的有三种：
        application/x-www-form-urlencoded 数据是个普通表单
        multipart/form-data 数据里有文件
        application/json 数据是个json

   原文链接：http://www.jianshu.com/p/1873287eed87
 */
public class BasicParmsInterceptor implements Interceptor {

    private static String TAG = BasicParmsInterceptor.class.getSimpleName();

    Map<String, String> queryParamsMap = new HashMap<>();
    Map<String, String> paramsMap = new HashMap<>();
    //Header Parms
    Map<String, String> headerParamsMap = new HashMap<>();
    List<String> headerLinesList = new ArrayList<>();

    private BasicParmsInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        //add header params
        Headers.Builder headerBuilder = request.headers().newBuilder();
        if(headerParamsMap.size() > 0){
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if(headerLinesList.size() > 0){
            for (String line : headerLinesList){
                headerBuilder.add(line);
            }
            requestBuilder.headers(headerBuilder.build());
        }


        if(queryParamsMap.size() > 0){
            request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, queryParamsMap);
        }

        if(paramsMap.size() > 0){
            if(canInjectIntoBody(request)){
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : paramsMap.entrySet()){
                    formBodyBuilder.add(entry.getKey(), entry.getValue());
                }

                RequestBody requestBody = formBodyBuilder.build();
                String postBodyString = bodyToString(request.body());
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(requestBody);
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
                Log.i(TAG, "--api name: "+request.url()+"\n--request param: "+postBodyString);
            }
        }

        request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String bodyToString(RequestBody body) {
        try {
            RequestBody copy = body;
            Buffer buffer = new Buffer();
            if(copy != null){
                copy.writeTo(buffer);
            }else {
                return "";
            }
            return buffer.readUtf8();
        }catch (IOException e) {
            return "post body String throw exception";
        }

    }

    private boolean canInjectIntoBody(Request request) {
        if(request == null){
            return false;
        }
        if(!TextUtils.equals(request.method(), "POST")){
            return false;
        }
        RequestBody body = request.body();
        if(body == null){
            return false;
        }
        MediaType mediaType = body.contentType();
        if(mediaType == null){
            return false;
        }
        if(!TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded")){
            return false;
        }
        return true;
    }

    private Request injectParamsIntoUrl(HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder,
                                        Map<String, String> queryParamsMap) {
        if(queryParamsMap.size() > 0){
            Iterator iterator = queryParamsMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            requestBuilder.url(httpUrlBuilder.build());
            return requestBuilder.build();
        }

        return null;

    }


    public static class Builder{
        BasicParmsInterceptor interceptor;

        public Builder(){
            interceptor = new BasicParmsInterceptor();
        }

        public Builder addParm(String key, String value){
            interceptor.paramsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap){
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine){
            int index = headerLine.indexOf(":");
            if(index == -1){
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine: headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public BasicParmsInterceptor build() {
            return interceptor;
        }
    }
}
