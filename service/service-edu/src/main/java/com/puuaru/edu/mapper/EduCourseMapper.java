package com.puuaru.edu.mapper;

import com.puuaru.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puuaru.servicebase.vo.CourseFrontInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishInfo getCoursePublishInfo(Long id);

    CourseFrontInfo getCourseFrontInfo(Long id);

    void updateCourseView(Long id, int increment);
}
