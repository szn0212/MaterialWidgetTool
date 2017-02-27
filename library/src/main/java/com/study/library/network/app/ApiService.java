package com.study.library.network.app;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 */
public interface ApiService {

    @POST(Api.API_CHECK_VERSION)
    @FormUrlEncoded
    Observable<ResponseBody> checkVersion(@FieldMap HashMap<String,String> params);


}
