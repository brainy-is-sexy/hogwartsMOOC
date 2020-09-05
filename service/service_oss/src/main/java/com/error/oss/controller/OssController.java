package com.error.oss.controller;

import com.error.commonutils.R;
import com.error.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
