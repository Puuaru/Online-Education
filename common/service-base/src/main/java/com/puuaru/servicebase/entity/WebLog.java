package com.puuaru.servicebase.entity;

import lombok.Data;

/**
 * @Description: WebLog Controller层的日志封装
 * @Author: puuaru
 * @Date: 2022/11/19
 */
@Data
public class WebLog {
    private String description;
    private String username;
    private Long startTime;
    private Integer spendTime;
    private String basePath;
    private String uri;
    private String url;
    private String method;
    private String ip;
    private Object parameter;
    private Object result;
}
