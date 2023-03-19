package com.puuaru.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Description: FeignUtils
 * @Author: puuaru
 * @Date: 2023/2/24
 */
public class FeignUtils {
    /**
     * 在 ResultCommon 中获取数据，根据 field 指定数据的 key
     * @param resultCommon
     * @param field
     * @param clazz
     * @return
     * @param <T>
     */
    public static  <T> T parseResult(ResultCommon resultCommon, String field, Class<T> clazz) {
        Object result = resultCommon.getData().get(field);
        String jsonString = JSON.toJSONString(result);
        T obj = JSON.parseObject(jsonString, clazz);
        return obj;
    }

    /**
     * 在 ResultCommon 中获取数据，不设置 field 的情况下默认以 “items” 作为数据 key
     * @param resultCommon
     * @param clazz
     * @return
     * @param <T>
     */
    public static  <T> T parseResult(ResultCommon resultCommon, Class<T> clazz) {
        Object result = resultCommon.getData().get("items");
        String jsonString = JSON.toJSONString(result);
        T obj = JSON.parseObject(jsonString, clazz);
        return obj;
    }
}
