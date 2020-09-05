package com.error.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.error.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.error.eduservice.entity.frontvo.CourseFrontVo;
import com.error.eduservice.entity.frontvo.CourseWebVo;
import com.error.eduservice.entity.vo.CourseInfoVo;
import com.error.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-08-31
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
