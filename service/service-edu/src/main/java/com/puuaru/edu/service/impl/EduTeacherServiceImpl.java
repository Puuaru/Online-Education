package com.puuaru.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.edu.entity.EduTeacher;
import com.puuaru.edu.mapper.EduTeacherMapper;
import com.puuaru.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.edu.vo.TeacherQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-11-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public Map<String, Object> getPage(long current, long limit, TeacherQuery query) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        if (query == null) {
            this.page(teacherPage);
            return getPageResult(teacherPage);
        }

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = query.getName();
        Integer level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();
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
        this.page(teacherPage, wrapper);
        return getPageResult(teacherPage);
    }

    private Map<String, Object> getPageResult(Page<EduTeacher> teacherPage) {
        long total = teacherPage.getTotal();
        List<EduTeacher> teachers = teacherPage.getRecords();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", teachers);
        return result;
    }
}
