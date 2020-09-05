package com.error.eduservice.controller;


import com.error.commonutils.R;
import com.error.eduservice.entity.EduCourse;
import com.error.eduservice.entity.vo.CoursePublishVo;
import com.error.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.error.eduservice.entity.vo.CourseInfoVo;

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
@RequestMapping("/eduservice/edu-course")
//@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @GetMapping
    public R getCourseList(){
        List<EduCourse> list=courseService.list(null);
        return R.ok().data("list",list);
    }


    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo){
        //返回课程id
        String id=courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public  R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R courseInfoVo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo=courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return  R.ok();
    }
}

