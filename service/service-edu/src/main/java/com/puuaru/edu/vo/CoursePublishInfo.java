package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: CoursePublishInfo
 * @Author: puuaru
 * @Date: 2022/12/30
 */
@Data
@Api("发布课程时的检查信息")
public class CoursePublishInfo {
    @ApiModelProperty(value = "课程id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程封面url")
    private String cover;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "一级专业名")
    private String subjectFirst;

    @ApiModelProperty(value = "二级专业名")
    private String subjectSecond;

    @ApiModelProperty(value = "教师名")
    private String teacherName;

    @ApiModelProperty(value = "课程费用")
    private String price;

}
