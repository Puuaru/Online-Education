package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseBackInfo;
import com.puuaru.edu.vo.CoursePublishInfo;
import com.puuaru.edu.vo.CourseBackQuery;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class EduCourseController {

    private final EduCourseService courseService;

    @Autowired
    public EduCourseController(EduCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 添加课程基本信息
     * @param courseBackInfo 课程基本信息
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加课程基本信息")
    public ResultCommon addCourseInfo(@RequestBody CourseBackInfo courseBackInfo) {
        String courseId = courseService.saveCourseInfo(courseBackInfo);
        return ResultCommon.success().setData("id", courseId);
    }

    /**
     * 根据课程id查询课程信息
     * @param id 课程id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据课程id查询课程信息")
    public CourseBackInfo getCourseInfo(@PathVariable("id") Long id) {
        CourseBackInfo courseBackInfo = courseService.getCourseInfo(id);
        return courseBackInfo;
    }

    /**
     * 更新课程信息，通过课程信息对象中的id指定课程
     * @param courseBackInfo 课程信息对象
     * @return
     */
    @PutMapping("")
    @ApiOperation("更新课程信息")
    public CourseBackInfo updateCourseInfo(@RequestBody CourseBackInfo courseBackInfo) {
        courseService.updateCourseInfo(courseBackInfo);
        return courseBackInfo;
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
     * @param courseBackQuery
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    @ApiOperation("条件分页查询课程")
    public ResultCommon getCoursePage(@PathVariable long current, @PathVariable long limit, @RequestBody CourseBackQuery courseBackQuery) {
        Map<String, Object> result = courseService.getPage(current, limit, courseBackQuery);
        return ResultCommon.success().setData(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除课程及其章节小节")
    public Boolean deleteCourse(@PathVariable Long id) {
        Boolean result = courseService.removeCourseById(id);
        return result;
    }
}
