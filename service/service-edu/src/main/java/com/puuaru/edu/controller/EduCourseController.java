package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.edu.vo.CourseQuery;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    private final EduCourseService courseService;

    @Autowired
    public EduCourseController(EduCourseService courseService) {
        this.courseService = courseService;
    }

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
    public CourseInfo getCourseInfo(@PathVariable("id") Long id) {
        CourseInfo courseInfo = courseService.getCourseInfo(id);
        return courseInfo;
    }

    /**
     * 更新课程信息，通过课程信息对象中的id指定课程
     * @param courseInfo 课程信息对象
     * @return
     */
    @PutMapping("")
    @ApiOperation("更新课程信息")
    public CourseInfo updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        courseService.updateCourseInfo(courseInfo);
        return courseInfo;
    }

    /**
     * 发布课程时的回显信息
     * @param id 课程id
     * @return
     */
    @GetMapping("/publish/{id}")
    @ApiOperation("发布课程时的回显信息")
    public CoursePublishInfo getPublishInfo(@PathVariable Long id) {
        CoursePublishInfo coursePublishInfo = courseService.getCoursePublishInfo(id);
        return coursePublishInfo;
    }

    /**
     * 修改课程状态，正式发布课程
     * @param id
     * @return
     */
    @PutMapping("/publish/{id}")
    @ApiOperation("修改课程状态，正式发布课程")
    public Boolean publishCourse(@PathVariable Long id) {
        Boolean result = courseService.publishCourse(id);
        return result;
    }

    /**
     * 条件分页查询课程
     * @param current
     * @param limit
     * @param courseQuery
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    @ApiOperation("条件分页查询课程")
    public ResultCommon getCoursePage(@PathVariable long current, @PathVariable long limit, @RequestBody CourseQuery courseQuery) {
        Map<String, Object> result = courseService.getPage(current, limit, courseQuery);
        return ResultCommon.success().setData(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除课程及其章节小节")
    public Boolean deleteCourse(@PathVariable Long id) {
        Boolean result = courseService.removeCourseById(id);
        return result;
    }
}
