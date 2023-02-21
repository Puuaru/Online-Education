package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: CourseFrontInfo, 前台课程查询的分类条件信息
 * @Author: puuaru
 * @Date: 2023/2/5
 */
@Data
public class CourseFrontQuery {
    @ApiModelProperty(value = "课程专业ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectParentId;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;

    @ApiModelProperty(value = "销售数量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "创建时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "用于模糊查询课程名称")
    private String courseName;
}
