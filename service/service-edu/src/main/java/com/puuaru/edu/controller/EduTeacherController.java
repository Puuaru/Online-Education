package com.puuaru.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.edu.entity.EduTeacher;
import com.puuaru.edu.service.EduTeacherService;
import com.puuaru.edu.vo.TeacherQuery;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-11-17
 */
@RestController
@RequestMapping("/edu/teacher")
@Api(value = "teacher management")
@CrossOrigin
public class EduTeacherController {
    private final EduTeacherService eduTeacherService;

    @Autowired
    public EduTeacherController(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取教师信息")
    public EduTeacher getById(@PathVariable("id") @ApiParam(value = "teacher_id", name = "id", required = true) Long id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return teacher;
    }

    /**
     * 查询所有教师信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有教师信息")
    public List<EduTeacher> getList() {
        List<EduTeacher> list = eduTeacherService.list();
        return list;
    }

    /**
     * 根据id删除教师
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除教师")
    public Boolean removeById(@PathVariable("id") @ApiParam(value = "teacher_id", name = "id", required = true) Long id) {
        return eduTeacherService.removeById(id);
    }

    /**
     * 添加教师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加教师")
    public Boolean add(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher);
    }

    /**
     * 根据id修改教师数据
     *
     * @param eduTeacher
     * @return
     */
    @PutMapping("")
    @ApiOperation(value = "根据id修改教师数据")
    public Boolean updateTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.updateById(eduTeacher);
    }

    /**
     * 分页查询教师
     *
     * @param current 当前页码
     * @param limit   每页数据条数上限
     * @return
     */
    @GetMapping("/page/{current}/{limit}")
    @ApiOperation(value = "分页查询教师")
    public ResultCommon getPage(@PathVariable("current") long current, @PathVariable("limit") long limit) {
        Map<String, Object> result = eduTeacherService.getPage(current, limit, null);
        return ResultCommon.success().setData(result);
    }

    /**
     * 根据条件分页查询教师
     *
     * @param current
     * @param limit
     * @param teacherQuery 教师查询的封装信息
     * @return
     */
    @PostMapping("/page/condition/{current}/{limit}")
    @ApiOperation(value = "根据条件分页查询教师")
    public ResultCommon getPageCondition(@PathVariable("current") long current, @PathVariable("limit") long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Map<String, Object> result = eduTeacherService.getPage(current, limit, teacherQuery);
        return ResultCommon.success().setData(result);
    }
}

