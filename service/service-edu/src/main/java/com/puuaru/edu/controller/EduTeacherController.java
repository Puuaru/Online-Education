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
    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取教师信息")
    public ResultCommon getById(@PathVariable("id") @ApiParam(value = "teacher_id", name = "id", required = true) Long id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return ResultCommon.success().setData("items", teacher);
    }

    /**
     * 查询所有教师信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有教师信息")
    public ResultCommon getList() {
        List<EduTeacher> list = eduTeacherService.list();
        return ResultCommon.success().setData("items", list);
    }

    /**
     * 根据id删除教师
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除教师")
    public ResultCommon removeById(@PathVariable("id") @ApiParam(value = "teacher_id", name = "id", required = true) Long id) {
        return eduTeacherService.removeById(id) ? ResultCommon.success() : ResultCommon.fail();
    }

    /**
     * 添加教师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加教师")
    public ResultCommon add(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? ResultCommon.success() : ResultCommon.fail();
    }

    /**
     * 根据id修改教师数据
     *
     * @param eduTeacher
     * @return
     */
    @PutMapping("")
    @ApiOperation(value = "根据id修改教师数据")
    public ResultCommon updateTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.updateById(eduTeacher) ? ResultCommon.success() : ResultCommon.fail();
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
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher);

        return getPageResultCommon(pageTeacher);
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
    @CrossOrigin
    public ResultCommon getPageCondition(@PathVariable("current") long current, @PathVariable("limit") long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (StringUtils.hasText(name)) {
            wrapper.like("name", name);
        }
        if (!ObjectUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.hasText(begin)) {
            wrapper.gt("gmt_create", begin);
        }
        if (StringUtils.hasText(end)) {
            wrapper.le("gmt_modified", end);
        }

        eduTeacherService.page(pageTeacher, wrapper);
        return getPageResultCommon(pageTeacher);
    }

    private ResultCommon getPageResultCommon(Page<EduTeacher> pageTeacher) {
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", records);
        return ResultCommon.success().setData(map);
    }
}
