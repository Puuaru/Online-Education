package com.puuaru.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.eduservice.entity.EduTeacher;
import com.puuaru.eduservice.service.EduTeacherService;
import com.puuaru.eduservice.vo.TeacherQuery;
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
@RequestMapping("/eduservice/teacher")
@Api(value = "teacher mangament")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    /**
     * 查询所有教师信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "list all teachers")
    public ResultCommon getList() {
        return ResultCommon.success().setData("items", eduTeacherService.list());
    }

    /**
     * 根据id删除教师
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "remove teacher by id")
    public ResultCommon removeById(@PathVariable("id") @ApiParam(value = "teacher_id", name = "id", required = true) Long id) {
        return eduTeacherService.removeById(id) ? ResultCommon.success() : ResultCommon.fail();
    }

    /**
     * 添加教师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("add teacher")
    public ResultCommon add(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? ResultCommon.success() : ResultCommon.fail();
    }

    /**
     * 根据id修改教师数据
     * @param eduTeacher
     * @return
     */
    @PutMapping("")
    @ApiOperation(value = "update teacher by id")
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
    @ApiOperation(value = "get all teachers into page")
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
    @ApiOperation(value = "get teachers according to query into page")
    public ResultCommon getPageCondition(@PathVariable("current") long current, @PathVariable("limit") long limit, @RequestBody TeacherQuery teacherQuery) {
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

