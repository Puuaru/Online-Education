package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVO> getCourseDetails(Long courseId);

    Boolean removeChapterById(Long chapterId);
}
