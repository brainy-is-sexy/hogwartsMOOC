package com.error.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAly(MultipartFile file);
    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList);
}
