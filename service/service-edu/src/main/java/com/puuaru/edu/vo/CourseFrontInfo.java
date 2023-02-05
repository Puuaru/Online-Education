package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: CourseFrontInfo
 * @Author: puuaru
 * @Date: 2023/2/5
 */
@Data
public class CourseFrontInfo {
    @ApiModelProperty(value = "课程专业ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectParentId;

    @ApiModelProperty(value = "价格排序")
    private BigDecimal priceSort;

    @ApiModelProperty(value = "销售数量排序")
    private Long buyCountSort;

    @ApiModelProperty(value = "创建时间排序")
    private String gmtCreateSort;
}
