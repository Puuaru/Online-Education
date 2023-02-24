package com.puuaru.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Description: FeignUtils
 * @Author: puuaru
 * @Date: 2023/2/24
 */
public class FeignUtils {
    public static  <T> T parseResult(ResultCommon resultCommon, String field, Class<T> clazz) {
        Object result = resultCommon.getData().get(field);
        String jsonString = JSON.toJSONString(result);
        T obj = JSON.parseObject(jsonString, clazz);
        return obj;
    }
}
