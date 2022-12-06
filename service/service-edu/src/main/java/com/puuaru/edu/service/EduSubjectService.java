package com.puuaru.edu.service;

import com.puuaru.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.edu.entity.vo.SubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author puuaru
 * @since 2022-11-28
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<SubjectVO> getSubjectTree();
}
