package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduChapterService;
import com.puuaru.utils.ResultCommon;
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
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService chapterService;

    @GetMapping("/details/{id}")
    public ResultCommon getCourseDetails(@PathVariable("id") Long courseId) {
        return ResultCommon.success().setData("items", chapterService.getCourseDetails(courseId));
    }
}

