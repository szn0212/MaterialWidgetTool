package com.study.library.network.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 */
public class JavaNetCookieJar implements CookieJar {

    private PersistentCookieStore cookieStore;

    public JavaNetCookieJar(Context context){
        if(cookieStore == null){
            cookieStore = new PersistentCookieStore(context);
        }
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }
}
