package com.puuaru.edu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: ChapterVO
 * @Author: puuaru
 * @Date: 2022/12/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterVO {
    @JsonSerialize(using = ToStringSerializer.class)
    Long id;
    String title;
    List<ChapterVO> children;   // 用以表示：1. 章节下的小节视频 2. 小节可能会有的更小小节
}
