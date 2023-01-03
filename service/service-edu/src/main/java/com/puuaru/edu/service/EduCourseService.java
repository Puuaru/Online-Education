package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.edu.vo.CourseQuery;

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

    CourseInfo getCourseInfo(Long id);

    void updateCourseInfo(CourseInfo courseInfo);

    CoursePublishInfo getCoursePublishInfo(Long id);

    Boolean publishCourse(Long id);

    Map<String, Object> getPage(long current, long limit, CourseQuery query);

    Boolean removeCourseById(Long id);
}
