package com.error.eduservice.controller;


import com.error.commonutils.R;
import com.error.eduservice.entity.EduChapter;
import com.error.eduservice.entity.chapter.ChapterVo;
import com.error.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public  R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list=chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter=chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    @PostMapping("updateChapterInfo")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag=chapterService.deleteChapter(chapterId);
        if(flag)
            return R.ok();
        else
            return R.error();
    }
}

