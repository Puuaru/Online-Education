package com.puuaru.edu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description: CourseFrontInfo
 * @Author: puuaru
 * @Date: 2023/2/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFrontInfo {
    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "课程讲师ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    @ApiModelProperty(value = "讲师名")
    private String teacherName;

    @ApiModelProperty(value = "讲师介绍")
    private String teacherIntro;

    @ApiModelProperty(value = "讲师头像")
    private String teacherAvatar;

    @ApiModelProperty(value = "课程二级分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

    @ApiModelProperty(value = "课程二级分类名")
    private String subjectTitle;

    @ApiModelProperty(value = "课程一级分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectParentId;

    @ApiModelProperty(value = "课程一级分类名")
    private String subjectParentTitle;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

}
