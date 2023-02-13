package com.puuaru.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.edu.entity.EduCourse;
import com.puuaru.edu.entity.EduCourseDescription;
import com.puuaru.edu.mapper.EduCourseMapper;
import com.puuaru.edu.service.EduChapterService;
import com.puuaru.edu.service.EduCourseDescriptionService;
import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.service.EduVideoService;
import com.puuaru.edu.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

    private final EduVideoService videoService;

    private final EduChapterService chapterService;

    private final EduCourseMapper courseMapper;

    @Autowired
    public EduCourseServiceImpl(EduCourseDescriptionService courseDescriptionService, EduVideoService videoService, EduChapterService chapterService, EduCourseMapper courseMapper) {
        this.courseDescriptionService = courseDescriptionService;
        this.videoService = videoService;
        this.chapterService = chapterService;
        this.courseMapper = courseMapper;
    }

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
    public Boolean removeCourseById(Long courseId) {
        // 考虑到查询计数带来的性能损失，只通过course表的删除结果判断是否成功执行
        Boolean result = this.removeById(courseId);
        courseDescriptionService.removeById(courseId);

        QueryWrapper chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        chapterService.remove(chapterWrapper);
        videoService.removeVideosByCourseId(courseId);

        return result;
    }

    @Override
    public List<EduCourse> getListByTeacherId(Long teacherId) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courses = super.list(wrapper);
        return courses;
    }

    @Override
    public Map<String, Object> getCoursesPageByCondition(long current, long limit, CourseFrontQuery courseFrontQuery) {
        Page<EduCourse> page = new Page<>();
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(courseFrontQuery.getSubjectParentId())) {
            // 一级分类
            wrapper.eq("subject_parent_id", courseFrontQuery.getSubjectParentId());
            if (!ObjectUtils.isEmpty(courseFrontQuery.getSubjectId())) {
                // 二级分类
                wrapper.eq("subject_id", courseFrontQuery.getSubjectId());
            }
        }
        if (!ObjectUtils.isEmpty(courseFrontQuery.getPriceSort())) {
            // 价格排序
            wrapper.orderByAsc("price");
        }
        if (!ObjectUtils.isEmpty(courseFrontQuery.getBuyCountSort())) {
            // 购买排序
            wrapper.orderByDesc("buy_count");
        }
        if (!ObjectUtils.isEmpty(courseFrontQuery.getGmtCreateSort())) {
            // 创建时间排序
            wrapper.orderByDesc("gmt_create");
        }
        if (!ObjectUtils.isEmpty(courseFrontQuery.getCourseName())) {
            // 模糊查找课程名
            wrapper.like("title", courseFrontQuery.getCourseName());
        }
        Page<EduCourse> pageResult = super.page(page, wrapper);

        return getPageResult(pageResult);
    }

    @Override
    public CourseFrontInfo getCourseFrontInfo(Long id) {
        CourseFrontInfo result = courseMapper.getCourseFrontInfo(id);
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
