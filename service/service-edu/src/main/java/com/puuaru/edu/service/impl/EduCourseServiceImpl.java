package com.puuaru.edu.service.impl;

import com.puuaru.edu.entity.EduCourse;
import com.puuaru.edu.entity.EduCourseDescription;
import com.puuaru.edu.mapper.EduCourseMapper;
import com.puuaru.edu.service.EduCourseDescriptionService;
import com.puuaru.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.edu.vo.CourseInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    /**
     * 根据课程信息VO类添加课程信息
     *
     * @param courseInfo
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        EduCourse addingCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, addingCourse);
        boolean result = this.save(addingCourse);
        if (!result) {
            throw new RuntimeException("添加课程信息失败");
        }

        Long courseId = addingCourse.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescription.setId(courseId);
        result = courseDescriptionService.save(courseDescription);
        if (!result) {
            throw new RuntimeException("添加课程简介失败");
        }
        // 存储id提供给前端
        return addingCourse.getId().toString();
    }
}
