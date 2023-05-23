package com.puuaru.servicebase.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description: PageData
 * @Author: puuaru
 * @Date: 2023/5/22
 */
@ApiModel("分页数据")
@Data
@AllArgsConstructor
public class PageData<T> {
    @ApiModelProperty("分页数据内容")
    private List<T> items;

    @ApiModelProperty("数据总量")
    private Long total;
}
