package com.puuaru.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ResultCommon
 * @Author: puuaru
 * @Date: 2022/11/18
 */
@Data
@Accessors(chain = true)
public class ResultCommon implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static int SUCCESS = 200;

    public static int ERROR = 404;

    @ApiModelProperty("返回码")
    private int code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();

    public ResultCommon() {
    }

    public static ResultCommon success() {
        ResultCommon resultCommon = new ResultCommon();
        resultCommon.setCode(SUCCESS);
        resultCommon.setMessage("成功");
        return resultCommon;
    }

    public static ResultCommon fail() {
        ResultCommon resultCommon = new ResultCommon();
        resultCommon.setCode(ERROR);
        resultCommon.setMessage("失败");
        return resultCommon;
    }

    public ResultCommon setData(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}
