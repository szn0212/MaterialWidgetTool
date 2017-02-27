package com.study.library.network.library;


import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * @author suzhuning
 * @date 2016/12/8.
 * Description:
 */
public class ServerResponseBean {

    public String error;
    public JsonObject results;
    public String apiName;
    public String apiFrom;//没有设置时为null
    public int retCode;//返回的码-500表示无网络连接
    public HashMap<String,String> params; //没有设置时为null


}
