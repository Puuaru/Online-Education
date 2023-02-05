package com.puuaru.edu.controller;

import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseFrontInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: FrontCourseController
 * @Author: puuaru
 * @Date: 2023/2/5
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/front/course")
public class FrontCourseController {
    private final EduCourseService courseService;

    public FrontCourseController(EduCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 根据分类或排序条件分页查询课程
     * @param current
     * @param limit
     * @param courseFrontInfo
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    @ApiOperation("根据分类或排序条件分页查询课程")
    public Map<String, Object> getCoursesPageByCondition(@PathVariable("current") long current,
                                                     @PathVariable("limit") long limit,
                                                     @RequestBody CourseFrontInfo courseFrontInfo) {
        Map<String, Object> result = courseService.getCoursesPageByCondition(current, limit, courseFrontInfo);
        return result;
    }
}
