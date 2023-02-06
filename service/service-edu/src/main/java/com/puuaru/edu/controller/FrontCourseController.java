package com.puuaru.edu.controller;

import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.CourseFrontInfo;
import com.puuaru.edu.vo.CourseFrontQuery;
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
     * @param courseFrontQuery
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    @ApiOperation("根据分类或排序条件分页查询课程")
    public Map<String, Object> getCoursesPageByCondition(@PathVariable("current") long current,
                                                     @PathVariable("limit") long limit,
                                                     @RequestBody CourseFrontQuery courseFrontQuery) {
        Map<String, Object> result = courseService.getCoursesPageByCondition(current, limit, courseFrontQuery);
        return result;
    }

    /**
     * 查询课程详细信息
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("查询课程详细信息")
    public CourseFrontInfo getCourseFrontInfo(@PathVariable("id") Long id) {
        CourseFrontInfo result = courseService.getCourseFrontInfo(id);
        return result;
    }
}
