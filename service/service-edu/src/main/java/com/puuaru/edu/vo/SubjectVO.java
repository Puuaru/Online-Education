package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: SubjectVo
 * @Author: puuaru
 * @Date: 2022/11/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private List<SubjectVO> children;
}
