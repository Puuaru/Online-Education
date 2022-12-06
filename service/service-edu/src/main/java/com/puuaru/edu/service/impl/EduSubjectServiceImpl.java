package com.puuaru.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.puuaru.edu.entity.EduSubject;
import com.puuaru.edu.entity.excel.ExcelSubjectData;
import com.puuaru.edu.vo.SubjectVO;
import com.puuaru.edu.listener.SubjectExcelListener;
import com.puuaru.edu.mapper.EduSubjectMapper;
import com.puuaru.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SubjectVO> getSubjectTree() {
        List<EduSubject> subjectList = super.list();
        List<SubjectVO> result = subjectList.stream()
                .filter(item -> item.getParentId() == 0L)   // 找出根节点
                .map(item -> new SubjectVO(item.getId(), item.getTitle(), null))    // 每个 EduSubject 转换为 VO 类
                .peek(item -> item.setChildren(getChildrenList(item, subjectList))) // 找出子分类
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 递归查询子节点
     * @param root
     * @param subjectList
     * @return
     */
    private List<SubjectVO> getChildrenList(SubjectVO root, List<EduSubject> subjectList) {
        List<SubjectVO> result = subjectList.stream()
                .filter(item -> item.getParentId().equals(root.getId()))    // 找出与父节点相同的节点
                .map(item -> new SubjectVO(item.getId(), item.getTitle(), null))    // 每个 EduSubject 转换为 VO 类
                .peek(item -> item.setChildren(getChildrenList(item, subjectList))) // 找出子分类
                .collect(Collectors.toList());
        return result;
    }
}
