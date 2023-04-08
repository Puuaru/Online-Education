package com.puuaru.edu.controller;


import com.puuaru.edu.service.EduSubjectService;
import com.puuaru.edu.vo.SubjectVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class EduSubjectController {

    private final EduSubjectService eduSubjectService;

    @Autowired
    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @PostMapping("")
    @ApiOperation("从 excel 文件中获取课程分类")
    public void saveSubject(@RequestPart("file") MultipartFile file) {
        eduSubjectService.saveSubject(file);
    }

    @GetMapping("")
    @ApiOperation("递归获取课程分类信息")
    @Cacheable(value = "subject")
    public List<SubjectVO> getSubject() {
        List<SubjectVO> subjectTree = eduSubjectService.getSubjectTree();
        return subjectTree;
    }
}

