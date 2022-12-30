package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
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
     * @param courseInfo 课程基本信息
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加课程基本信息")
    public ResultCommon addCourseInfo(@RequestBody CourseInfo courseInfo) {
        String courseId = courseService.saveCourseInfo(courseInfo);
        return ResultCommon.success().setData("id", courseId);
    }

    /**
     * 根据课程id查询课程信息
     * @param id 课程id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据课程id查询课程信息")
    public ResultCommon getCourseInfo(@PathVariable("id") Long id) {
        CourseInfo courseInfo = courseService.getCourseInfo(id);
        return ResultCommon.success().setData("items", courseInfo);
    }

    /**
     * 更新课程信息，通过课程信息对象中的id指定课程
     * @param courseInfo 课程信息对象
     * @return
     */
    @PutMapping("")
    @ApiOperation("更新课程信息")
    public ResultCommon updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        courseService.updateCourseInfo(courseInfo);
        return ResultCommon.success().setData("items", courseInfo);
    }

    /**
     * 发布课程时的回显信息
     * @param id 课程id
     * @return
     */
    @GetMapping("/publish/{id}")
    @ApiOperation("发布课程时的回显信息")
    public ResultCommon getPublishInfo(@PathVariable Long id) {
        CoursePublishInfo coursePublishInfo = courseService.getCoursePublishInfo(id);
        return ResultCommon.success().setData("items", coursePublishInfo);
    }
}
