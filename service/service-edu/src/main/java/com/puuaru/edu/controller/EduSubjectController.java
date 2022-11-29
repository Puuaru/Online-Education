package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduSubjectService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("")
    @ApiOperation("从 excel 文件中获取课程分类")
    public ResultCommon saveSubject(@RequestPart("file") MultipartFile file) {
        eduSubjectService.saveSubject(file);
        return ResultCommon.success();
    }

    @GetMapping("")
    @ApiOperation("递归获取课程分类信息")
    public ResultCommon getSubject() {
        // TODO
        return null;
    }
}

