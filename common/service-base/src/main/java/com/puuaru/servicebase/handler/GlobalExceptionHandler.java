package com.puuaru.servicebase.handler;

import com.puuaru.utils.ResultCommon;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: GlobalExceptionHandler
 * @Author: puuaru
 * @Date: 2022/11/19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理器
     * @param e
     * @return 作为响应体的结果封装类
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultCommon error(Exception e) {
        e.printStackTrace();
        return ResultCommon.fail().setMessage("Global exception caught");
    }
}
