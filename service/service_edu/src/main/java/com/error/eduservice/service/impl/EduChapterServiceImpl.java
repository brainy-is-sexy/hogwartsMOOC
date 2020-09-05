package com.error.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.error.eduservice.entity.EduChapter;
import com.error.eduservice.entity.EduVideo;
import com.error.eduservice.entity.chapter.ChapterVo;
import com.error.eduservice.entity.chapter.VideoVo;
import com.error.eduservice.mapper.EduChapterMapper;
import com.error.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.error.eduservice.service.EduVideoService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-08-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList=baseMapper.selectList(wrapperChapter);

        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList=videoService.list(wrapperVideo);

        List<ChapterVo> finalList=new ArrayList<>();

        for(int i=0;i<eduChapterList.size();i++){
            EduChapter eduChapter=eduChapterList.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoVoList=new ArrayList<>();

            for(int m=0;m<eduVideoList.size();m++){
                EduVideo eduVideo=eduVideoList.get(m);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }


        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        int result = 0;
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count=videoService.count(wrapper);
        if(count>0){

        }else{
            result=baseMapper.deleteById(chapterId);
        }
        return result>0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
