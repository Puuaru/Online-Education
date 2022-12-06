package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.CourseInfo;

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
     * @param courseInfo
     */
    void saveCourseInfo(CourseInfo courseInfo);
}
