package com.puuaru.utils;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ResponseUtil
 * @Author: puuaru
 * @Date: 2023/5/6
 */
public class ResponseUtil {
    @SneakyThrows
    public static void out(HttpServletResponse response, ResultCommon result) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        JSON.writeJSONString(response.getWriter(), result);
    }
}
