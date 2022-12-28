package com.puuaru.edu.controller;


import com.puuaru.edu.entity.EduChapter;
import com.puuaru.edu.service.EduChapterService;
import com.puuaru.edu.vo.ChapterVO;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService chapterService;

    /**
     * 根据课程id获取某课程的章节和视频（小节）数据，作用上类似 getList
     * @param courseId
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("根据课程id获取某课程的章节和视频（小节）数据")
    public ResultCommon getCourseDetails(@PathVariable("id") Long courseId) {
        List<ChapterVO> courseDetails = chapterService.getCourseDetails(courseId);
        return ResultCommon.success().setData("items", courseDetails);
    }

    /**
     * 根据章节id获取章节信息
     * @param chapterId
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据章节id获取章节信息")
    public ResultCommon getChapterById(@PathVariable("id") Long chapterId) {
        EduChapter chapter = chapterService.getById(chapterId);
        return ResultCommon.success().setData("items", chapter);
    }

    /**
     * 新增章节信息
     * @param chapter
     * @return
     */
    @PostMapping("")
    @ApiOperation("新增章节信息")
    public ResultCommon saveChapter(@RequestBody EduChapter chapter) {
        chapterService.save(chapter);
        return ResultCommon.success();
    }

    /**
     * 更新章节信息
     * @param chapter
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation("更新章节信息")
    public ResultCommon updateChapterById(@RequestBody EduChapter chapter) {
        chapterService.updateById(chapter);
        return ResultCommon.success().setData("items", chapter);
    }

    /**
     * 根据章节id删除章节及其小节信息
     * @param chapterId
     * @return
     */
    @DeleteMapping("")
    @ApiOperation("根据章节id删除章节及其小节信息")
    public ResultCommon deleteChapterById(@PathVariable Long chapterId) {
        chapterService.deleteById(chapterId);
        return ResultCommon.success();
    }
}

