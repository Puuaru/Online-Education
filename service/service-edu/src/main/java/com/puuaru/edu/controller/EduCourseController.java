package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/edu/course")
@Api("课程信息管理")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService courseService;

    /**
     * 添加课程基本信息
     * @param courseInfo
     * @return
     */
    @PostMapping("")
    public ResultCommon addCourseInfo(@RequestBody CourseInfo courseInfo) {
        String id = courseService.saveCourseInfo(courseInfo);
        return ResultCommon.success().setData("id", id);
    }

}

