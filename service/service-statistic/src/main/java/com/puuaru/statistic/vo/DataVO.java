package com.puuaru.statistic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: DataVO
 * @Author: puuaru
 * @Date: 2023/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataVO {
    private String type;
    private List<Integer> data;
}
