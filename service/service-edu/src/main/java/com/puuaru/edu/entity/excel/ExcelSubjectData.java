package com.puuaru.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: ExcelSubjectData 用于存储数据的课程分类实体类
 * @Author: puuaru
 * @Date: 2022/11/28
 */
@Data
public class ExcelSubjectData {

    @ExcelProperty(index = 0)
    private String firstSubjectName;

    @ExcelProperty(index = 1)
    private String secondSubjectName;
}
