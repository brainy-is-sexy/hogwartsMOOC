package com.error.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.error.eduservice.entity.EduSubject;
import com.error.eduservice.entity.excel.SubjectData;
import com.error.eduservice.entity.subject.OneSubject;
import com.error.eduservice.entity.subject.TwoSubject;
import com.error.eduservice.listener.SubjectExcelListener;
import com.error.eduservice.mapper.EduSubjectMapper;
import com.error.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-08-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //课程分类
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1查询所有一级
        QueryWrapper<EduSubject> wrapperOne=new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList=baseMapper.selectList(wrapperOne);

        //2查询所有2级分类
        QueryWrapper<EduSubject> wrapperTwo=new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);
        List<EduSubject> twoSubjectList=baseMapper.selectList(wrapperTwo);

        List<OneSubject> finalSubjectList=new ArrayList<>();

        //3.封装一级
        for(int i=0;i<oneSubjectList.size();i++){
            EduSubject eduSubject=oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();

            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            List<TwoSubject> twofinalSubjectList=new ArrayList<>();
            for(int m=0;m<twoSubjectList.size();m++){
                EduSubject tSubject=twoSubjectList.get(m);
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twofinalSubjectList.add(twoSubject);
                }
            }

            oneSubject.setChildren(twofinalSubjectList);
        }

        return finalSubjectList;
    }
}
