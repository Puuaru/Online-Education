package com.puuaru.edu.controller;

import com.puuaru.edu.service.EduChapterService;
import com.puuaru.edu.service.EduCourseService;
import com.puuaru.edu.vo.ChapterVO;
import com.puuaru.edu.vo.CourseFrontInfo;
import com.puuaru.edu.vo.CourseFrontQuery;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    private final EduChapterService chapterService;

    @Autowired
    public FrontCourseController(EduCourseService courseService, EduChapterService chapterService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
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
    public ResultCommon getCoursesPageByCondition(@PathVariable("current") long current,
                                                  @PathVariable("limit") long limit,
                                                  @RequestBody CourseFrontQuery courseFrontQuery) {
        Map<String, Object> result = courseService.getCoursesPageByCondition(current, limit, courseFrontQuery);
        return ResultCommon.success().setData(result);
    }

    /**
     * 查询课程详细信息
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("查询课程详细信息")
    @Cacheable(value = "courseDetails", key = "#id")
    public ResultCommon getCourseFrontInfo(@PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<>();
        CourseFrontInfo details = courseService.getCourseFrontInfo(id);
        List<ChapterVO> chapters = chapterService.getCourseChapter(id);
        result.put("details", details);
        result.put("chapters", chapters);
        return ResultCommon.success().setData(result);
    }
}
