package com.puuaru.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.edu.entity.EduSubject;
import com.puuaru.edu.entity.excel.ExcelSubjectData;
import com.puuaru.edu.service.EduSubjectService;

/**
 * @Description: SubjectExcelListener EasyExcel的读监听器不能被 Spring 管理，每次读取 excel 都要 new
 * Excel文件的表头应为 一级分类、二级分类
 * @Author: puuaru
 * @Date: 2022/11/29
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    // 由于监听器不被 spring 管理，无法使用自动注入的方式获取业务类或 Mapper，选择使用构造器注入
    private EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        // 读取到一行后进行数据处理
        if (excelSubjectData == null) {
            throw new NullPointerException("文件数据为空");
        }

        EduSubject eduSubject = existFirstSubject(excelSubjectData.getFirstSubjectName());
        if (eduSubject == null) {
            // 不存在当前一级分类的情况下添加进数据库
            eduSubject = new EduSubject();
            eduSubject.setParentId(0L);
            eduSubject.setTitle(excelSubjectData.getFirstSubjectName());
            subjectService.save(eduSubject);
        }

        Long parentId = eduSubject.getId();
        eduSubject = existSecondSubject(excelSubjectData.getSecondSubjectName(), parentId);
        if (eduSubject == null) {
            // 不存在当前二级分类的情况下添加进数据库
            eduSubject = new EduSubject();
            eduSubject.setParentId(parentId);
            eduSubject.setTitle(excelSubjectData.getSecondSubjectName());
            subjectService.save(eduSubject);
        }
    }

    /**
     * 判断是否存在一级分类
     *
     * @param firstSubjectName
     * @return
     */
    private EduSubject existFirstSubject(String firstSubjectName) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "parent_id")
                .eq("title", firstSubjectName);
        return subjectService.getOne(wrapper);
    }

    /**
     * 判断是否存在二级分类
     *
     * @param secondSubjectName
     * @param parentId
     * @return
     */
    private EduSubject existSecondSubject(String secondSubjectName, Long parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "parent_id")
                .eq("title", secondSubjectName)
                .eq("parent_id", parentId);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
