package com.puuaru.eduservice.controller;


import com.puuaru.eduservice.entity.EduTeacher;
import com.puuaru.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-11-17
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(value = "teacher mangament")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/list")
    public List<EduTeacher> getTeacherList() {
        return eduTeacherService.list();
    }

    @DeleteMapping("/remove/{id}")
    public Object removeTeacherById(@PathVariable("id") Long id) {
        return eduTeacherService.removeById(id);
    }
}

