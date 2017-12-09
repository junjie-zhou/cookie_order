package com.cookie.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by myseital  on 2017/11/21.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
