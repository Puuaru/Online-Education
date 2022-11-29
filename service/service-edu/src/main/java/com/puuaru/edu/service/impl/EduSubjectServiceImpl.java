package com.puuaru.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.puuaru.edu.entity.EduSubject;
import com.puuaru.edu.entity.excel.ExcelSubjectData;
import com.puuaru.edu.listener.SubjectExcelListener;
import com.puuaru.edu.mapper.EduSubjectMapper;
import com.puuaru.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-11-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @SneakyThrows
    @Override
    public void saveSubject(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
    }
}
