package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author puuaru
 * @since 2022-11-17
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getPage(long current, long limit, TeacherQuery query);
}
