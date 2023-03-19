package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: CourseInfo
 * @Author: puuaru
 * @Date: 2022/12/6
 */
@Data
@Api("用于接受管理系统的前端返回的课程信息的VO类")
public class CourseBackInfo {

    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "课程讲师ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    @ApiModelProperty(value = "课程专业ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
}
