package com.puuaru.servicebase.handler;

import com.puuaru.servicebase.exception.FeignException;
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

    @ResponseBody
    @ExceptionHandler(FeignException.class)
    public ResultCommon feignExceptionHandler(FeignException e) {
        e.printStackTrace();
        return ResultCommon.fail().setMessage(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResultCommon RuntimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return ResultCommon.fail().setMessage(e.getMessage());
    }

    /**
     * 通用异常处理器
     * @param e
     * @return 作为响应体的结果封装类
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultCommon globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultCommon.fail().setMessage("Global exception caught");
    }
}
