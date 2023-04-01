package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
public interface EduVideoService extends IService<EduVideo> {
    Boolean removeVideo(Long videoId);

    Boolean removeVideosByCourseId(Long courseId);

    Boolean removeVideosByChapterId(Long chapterId);

    void updateVideosViewBySourceId(String sourceId);
}
