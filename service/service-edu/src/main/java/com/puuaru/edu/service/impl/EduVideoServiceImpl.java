package com.puuaru.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.edu.entity.EduVideo;
import com.puuaru.edu.feign.VodClient;
import com.puuaru.edu.mapper.EduVideoMapper;
import com.puuaru.edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@Service
@Transactional
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private final VodClient vodClient;

    @Autowired
    public EduVideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public Boolean removeVideo(Long videoId) {
        EduVideo video = this.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        boolean result = this.removeById(videoId);
        if (!StringUtils.hasText(videoSourceId)) {
            return result;
        }
        vodClient.deleteSourceVideo(videoSourceId);
        return result;
    }

    @Override
    public Boolean removeVideosByCourseId(Long courseId) {
        Boolean result = removeVideos(true, courseId);
        return result;
    }

    @Override
    public Boolean removeVideosByChapterId(Long chapterId) {
        Boolean result = removeVideos(false, chapterId);
        return result;
    }

    /**
     * 批量删除小节及视频
     * @param removeByCourse 是否通过courseId删除，否则为通过chapterId删除
     * @param id
     * @return
     */
    private Boolean removeVideos(boolean removeByCourse, Long id) {
        QueryWrapper wrapper = new QueryWrapper<>();
        if (removeByCourse) {
            wrapper.eq("course_id", id);
        } else {
            wrapper.eq("chapter_id", id);
        }
        List<EduVideo> videos = this.list(wrapper);
        String videoSourceIds = videos.stream()
                .map(item -> new String(item.getVideoSourceId()))
                .collect(Collectors.joining(","));
        boolean result = this.remove(wrapper);
        if (!StringUtils.hasText(videoSourceIds)) {
            return result;
        }
        vodClient.deleteSourceVideo(videoSourceIds);
        return result;
    }
}
