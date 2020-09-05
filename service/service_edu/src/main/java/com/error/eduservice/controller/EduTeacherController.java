package com.error.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.error.commonutils.R;
import com.error.eduservice.entity.EduTeacher;
import com.error.eduservice.entity.vo.TeacherQuery;
import com.error.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-28
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
//@CrossOrigin //解决跨域
public class EduTeacherController {

    //访问地址 http://localhost:8001/eduservice/edu-teacher/findAll

    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list=teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){
        boolean flag=teacherService.removeById(id);
        return flag?R.ok():R.error();
    }

    //3 分页查询讲师的方法
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);

        teacherService.page(pageTeacher, null);
        List<EduTeacher> records =  pageTeacher.getRecords();
        long total =  pageTeacher.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }



    //4 条件查询带分页方法
    @ApiOperation(value = "根据条件查询分页讲师列表")
    @PostMapping("pageTeacherCondition/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    @RequestBody(required = false)
                    TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();

        //判断条件值是否为空
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //排序
        wrapper.orderByAsc("sort");

        //调用方法
        teacherService.page(pageTeacher, wrapper);
        List<EduTeacher> records =pageTeacher.getRecords();
        long total = pageTeacher.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    //添加讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduteacher){

        boolean save=teacherService.save(eduteacher);
        return save?R.ok():R.error();
    }

    //根据id查询讲师
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher eduteacher = teacherService.getById(id);
        return R.ok().data("teacher", eduteacher);
    }


    //修改讲师
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher/{id}")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduteacher){

        boolean flag=teacherService.updateById(eduteacher);
        return flag?R.ok():R.error();
    }

    //修改讲师
    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduteacher){

        boolean flag=teacherService.updateById(eduteacher);
        return flag?R.ok():R.error();
    }
}

