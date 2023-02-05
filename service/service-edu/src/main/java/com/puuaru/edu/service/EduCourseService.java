package com.puuaru.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.CourseFrontInfo;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.edu.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 根据课程信息VO类添加课程信息
     *
     * @param courseInfo
     * @return
     */
    String saveCourseInfo(CourseInfo courseInfo);

    /**
     * 根据id获取课程
     * @param id
     * @return
     */
    CourseInfo getCourseInfo(Long id);

    /**
     * 更新课程信息
     * @param courseInfo
     */
    void updateCourseInfo(CourseInfo courseInfo);

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    CoursePublishInfo getCoursePublishInfo(Long id);

    /**
     * 发布课程
     * @param id
     * @return
     */
    Boolean publishCourse(Long id);

    /**
     * 后台管理系统查询课程分页
     * @param current
     * @param limit
     * @param query
     * @return
     */
    Map<String, Object> getPage(long current, long limit, CourseQuery query);

    /**
     * 通过课程id删除课程
     * @param id
     * @return
     */
    Boolean removeCourseById(Long id);

    /**
     * 通过教师id获取课程列表
     * @param teacherId
     * @return
     */
    List<EduCourse> getListByTeacherId(Long teacherId);

    /**
     * 前台系统根据条件分类查询课程分页
     * @param current
     * @param limit
     * @return
     */
    Page<EduCourse> getCoursePageByCondition(long current, long limit, CourseFrontInfo courseFrontInfo);
}
