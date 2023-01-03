package com.puuaru.servicebase.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puuaru.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: ResponseAdvice
 * @Author: puuaru
 * @Date: 2023/1/2
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 是否开启Advice
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 放行swagger
        return !methodParameter.getDeclaringClass().getName().contains("springfox");
    }

    /**
     * 统一处理返回结果，减少如ResultCommon.success().setData("items", obj)的代码
     * @param body
     * @param returnType
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof String) {
            // String类单独处理
            try {
                return objectMapper.writeValueAsString(ResultCommon.success().setData("msg", body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // 对于已封装的ResultCommon不进行处理，避免嵌套重复处理并提高灵活性
        if (body instanceof ResultCommon) {
            return body;
        }

        if (body == null) {
            return ResultCommon.success();
        }

        // 封装为ResultCommon，其中载荷为items
        return ResultCommon.success().setData("items", body);
    }
}
