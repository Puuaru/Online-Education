package com.puuaru.edu.controller;

import com.puuaru.edu.entity.EduCourse;
import com.puuaru.edu.entity.EduTeacher;
import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: FrontTeacherController
 * @Author: puuaru
 * @Date: 2023/2/5
 */
@RestController
@RequestMapping("/edu/front/teacher")
public class FrontTeacherController {
    private final EduTeacherService teacherService;
    private final EduCourseService courseService;

    @Autowired
    public FrontTeacherController(EduTeacherService teacherService, EduCourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    /**
     * 获取讲师详情
     * @param teacherId
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("获取讲师详情")
    public Map<String, Object> getTeacherDetail(@PathVariable("id") Long teacherId) {
        HashMap<String, Object> result = new HashMap<>();
        EduTeacher teacherInfo = teacherService.getById(teacherId);
        List<EduCourse> taughtCourses = courseService.getListByTeacherId(teacherId);
        result.put("teacher", teacherInfo);
        result.put("courses", taughtCourses);
        return result;
    }
}
