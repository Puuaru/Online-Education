package com.puuaru.edu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: CourseQuery
 * @Author: puuaru
 * @Date: 2023/1/3
 */
@Data
public class CourseBackQuery {
    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
}
