package com.puuaru.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.edu.entity.EduChapter;
import com.puuaru.edu.entity.EduVideo;
import com.puuaru.edu.mapper.EduChapterMapper;
import com.puuaru.edu.service.EduChapterService;
import com.puuaru.edu.service.EduVideoService;
import com.puuaru.edu.vo.ChapterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    //private final EduVideoService videoService;

    private final EduVideoService videoService;

    //@Autowired
    //public EduChapterServiceImpl(EduVideoService videoService) {
    //    this.videoService = videoService;
    //}

    @Autowired
    public EduChapterServiceImpl(EduVideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 将连同小节信息的整个章节信息删除
     *
     * @param chapterId
     * @return
     */
    @Override
    public Boolean deleteById(Long chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        List<EduVideo> videos = videoService.list(wrapper);
        videos.forEach(video -> videoService.removeVideo(video.getId()));
        videoService.remove(wrapper);
        // (?) delete videos from remote server

        // 考虑到查询小节条目数带来的性能损失，删除的成功与否以chapter删除的结果为依据
        Boolean result = super.removeById(chapterId);
        return result;
    }

    /**
     * 获取课程对应的所有章节和各章节对应的小节
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVO> getCourseDetails(Long courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> chapters = super.list(wrapper);
        // 根据sort字段进行排序
        chapters.sort(Comparator.comparing(EduChapter::getSort));

        return chapters.stream()
                .map(item -> new ChapterVO(item.getId(), item.getTitle(), null))
                .peek(item -> {
                    List<EduVideo> videoList = getChildVideoList(item);
                    item.setChildren(getVideoList(videoList));
                })
                .collect(Collectors.toList());
    }

    /**
     * 获取查询子节点列表并进行排序，在chapterService中，视Chapter实体类和Video实体类的chapterId字段为父节点字段
     * 从而保证可能在Video中进行更多的细分
     *
     * @param item
     * @return
     */
    private List<EduVideo> getChildVideoList(ChapterVO item) {
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id", item.getId());
        List<EduVideo> videoList = videoService.list(videoWrapper);
        // 根据sort字段进行排序
        videoList.sort(Comparator.comparing(EduVideo::getSort));
        return videoList;
    }

    /**
     * 递归查找作为子节点的视频（小节）列表
     *
     * @param videos
     * @return
     */
    private List<ChapterVO> getVideoList(List<EduVideo> videos) {
        return videos.stream()
                // 将Video实体类转换为ChapterVO类时会存放关于Video的更多信息
                .map(item -> new ChapterVO(item.getId(), item.getTitle(), null))
                .peek(item -> {
                    // 查询每个视频是否有分p
                    List<EduVideo> subVideoList = getChildVideoList(item);
                    item.setChildren(getVideoList(subVideoList));
                })
                .collect(Collectors.toList());
    }
}
