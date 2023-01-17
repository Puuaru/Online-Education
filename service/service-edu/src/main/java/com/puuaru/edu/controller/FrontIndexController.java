package com.puuaru.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.edu.entity.EduCourse;
import com.puuaru.edu.entity.EduTeacher;
import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.service.EduTeacherService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: FrontIndexController
 * @Author: puuaru
 * @Date: 2023/1/15
 */
@RequestMapping("/edu/front/index")
@RestController
@CrossOrigin
public class FrontIndexController {
    private final EduCourseService courseService;

    private final EduTeacherService teacherService;

    @Autowired
    public FrontIndexController(EduCourseService courseService, EduTeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    /**
     * 获取前8个热门课程及前4个热门教师用作客户端首页信息
     *
     * @return
     */
    @GetMapping("")
    @ApiOperation("获取前8个热门课程及前4个热门教师用作客户端首页信息")
    @Cacheable(value = "index", key = "'teacherAndCourse'")
    public ResultCommon getIndexInfo() {
        Map<String, Object> result = new HashMap<>();

        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("view_count").last("limit 8");
        List<EduCourse> courses = courseService.list(courseWrapper);
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("sort").last("limit 4");
        List<EduTeacher> teachers = teacherService.list(teacherWrapper);

        result.put("courses", courses);
        result.put("teachers", teachers);
        return ResultCommon.success().setData(result);
    }
}
