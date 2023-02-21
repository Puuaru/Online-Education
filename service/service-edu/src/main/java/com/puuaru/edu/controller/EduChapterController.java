package com.puuaru.edu.controller;


import com.puuaru.edu.entity.EduChapter;
import com.puuaru.edu.service.EduChapterService;
import com.puuaru.edu.vo.ChapterVO;
import io.swagger.annotations.ApiOperation;
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

    private final EduChapterService chapterService;

    @Autowired
    public EduChapterController(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     * 根据课程id获取某课程的章节和视频（小节）数据，作用上类似 getList
     * @param courseId 章节id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("根据课程id获取某课程的章节和视频（小节）数据")
    public List<ChapterVO> getCourseDetails(@PathVariable("id") Long courseId) {
        List<ChapterVO> courseDetails = chapterService.getCourseChapter(courseId);
        return courseDetails;
    }

    /**
     * 根据章节id获取章节信息
     * @param chapterId 章节id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据章节id获取章节信息")
    public EduChapter getChapterById(@PathVariable("id") Long chapterId) {
        EduChapter chapter = chapterService.getById(chapterId);
        return chapter;
    }

    /**
     * 新增章节信息
     * @param chapter 章节信息
     * @return
     */
    @PostMapping("")
    @ApiOperation("新增章节信息")
    public Boolean saveChapter(@RequestBody EduChapter chapter) {
        Boolean result = chapterService.save(chapter);
        return result;
    }

    /**
     * 更新章节信息
     * @param chapter 章节信息
     * @return
     */
    @PutMapping("")
    @ApiOperation("更新章节信息")
    public Boolean updateChapterById(@RequestBody EduChapter chapter) {
        Boolean result = chapterService.updateById(chapter);
        return result;
    }

    /**
     * 根据章节id删除章节及其小节信息
     * @param chapterId 章节id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据章节id删除章节及其小节信息")
    public Boolean deleteChapterById(@PathVariable("id") Long chapterId) {
        Boolean result = chapterService.removeChapterById(chapterId);
        return result;
    }
}

