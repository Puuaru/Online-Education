package com.puuaru.servicebase.exception;

/**
 * @Description: FeignException
 * @Author: puuaru
 * @Date: 2023/1/12
 */
public class FeignException extends RuntimeException {
    private String msg;

    public FeignException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
