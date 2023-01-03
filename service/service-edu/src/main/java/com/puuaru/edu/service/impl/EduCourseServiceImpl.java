package com.puuaru.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.edu.entity.EduCourse;
import com.puuaru.edu.entity.EduCourseDescription;
import com.puuaru.edu.entity.EduVideo;
import com.puuaru.edu.mapper.EduChapterMapper;
import com.puuaru.edu.mapper.EduCourseMapper;
import com.puuaru.edu.mapper.EduVideoMapper;
import com.puuaru.edu.service.EduCourseDescriptionService;
import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.edu.vo.CourseQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private final EduCourseDescriptionService courseDescriptionService;

    private final EduVideoMapper videoMapper;

    private final EduChapterMapper chapterMapper;

    @Autowired
    public EduCourseServiceImpl(EduCourseDescriptionService courseDescriptionService, EduVideoMapper videoMapper, EduChapterMapper chapterMapper) {
        this.courseDescriptionService = courseDescriptionService;
        this.videoMapper = videoMapper;
        this.chapterMapper = chapterMapper;
    }

    /**
     * 根据课程信息VO类添加课程信息
     *
     * @param courseInfo
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfo, course);
        boolean result = this.save(course);
        if (!result) {
            throw new RuntimeException("添加课程信息失败");
        }

        Long courseId = course.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescription.setId(courseId);
        result = courseDescriptionService.save(courseDescription);
        if (!result) {
            throw new RuntimeException("添加课程简介失败");
        }
        // 存储id提供给前端
        return course.getId().toString();
    }

    @Override
    public CourseInfo getCourseInfo(Long id) {
        CourseInfo courseInfo = new CourseInfo();
        EduCourse course = super.getById(id);
        EduCourseDescription courseDescription = courseDescriptionService.getById(id);

        if (course != null) {
            BeanUtils.copyProperties(course, courseInfo);
        }
        if (courseDescription != null) {
            BeanUtils.copyProperties(courseDescription, courseInfo);
        }

        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfo, course);
        super.updateById(course);

        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishInfo getCoursePublishInfo(Long id) {
        CoursePublishInfo coursePublishInfo = baseMapper.getCoursePublishInfo(id);
        return coursePublishInfo;
    }

    @Override
    public Boolean publishCourse(Long id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        Boolean result = this.updateById(course);
        return result;
    }

    @Override
    public Map<String, Object> getPage(long current, long limit, CourseQuery query) {
        Page<EduCourse> coursePage = new Page<>(current, limit);
        if (query == null) {
            this.page(coursePage);
            return getPageResult(coursePage);
        }
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "price", "lesson_num", "view_count", "status", "gmt_create");
        String title = query.getTitle();
        String status = query.getStatus();
        if (StringUtils.hasText(title)) {
            wrapper.like("title", title);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq("status", status);
        }
        this.page(coursePage, wrapper);
        return getPageResult(coursePage);
    }

    @Override
    public Boolean removeCourseById(Long id) {
        // 考虑到查询计数带来的性能损失，只通过course表的删除结果判断是否成功执行
        Boolean result = this.removeById(id);
        courseDescriptionService.removeById(id);

        QueryWrapper chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", id);
        chapterMapper.delete(chapterWrapper);

        // 获取视频源id，删除小节
        QueryWrapper videoWrapper = new QueryWrapper<>();
        videoWrapper.select("video_source_id").eq("course_id", id);
        List<EduVideo> videos = videoMapper.selectList(videoWrapper);
        videoWrapper.clear();
        videoWrapper.eq("course_id", id);
        videoMapper.delete(videoWrapper);
        videos.forEach(item -> {
            // TODO: 删除视频
        });

        return result;
    }

    private Map<String, Object> getPageResult(Page<EduCourse> coursePage) {
        long total = coursePage.getTotal();
        List<EduCourse> courses = coursePage.getRecords();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", courses);
        return result;
    }
}
