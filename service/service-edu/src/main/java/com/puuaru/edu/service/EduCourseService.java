package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.*;
import com.puuaru.servicebase.vo.CourseFrontInfo;

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
     * @param courseBackInfo
     * @return
     */
    String saveCourseInfo(CourseBackInfo courseBackInfo);

    /**
     * 根据id获取课程
     * @param id
     * @return
     */
    CourseBackInfo getCourseInfo(Long id);

    /**
     * 更新课程信息
     * @param courseBackInfo
     */
    void updateCourseInfo(CourseBackInfo courseBackInfo);

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
    Map<String, Object> getPage(long current, long limit, CourseBackQuery query);

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
    Map<String, Object> getCoursesPageByCondition(long current, long limit, CourseFrontQuery courseFrontQuery);

    /**
     * 前台系统查询课程详细信息
     * @param id
     * @return
     */
    CourseFrontInfo getCourseFrontInfo(Long id);
}
