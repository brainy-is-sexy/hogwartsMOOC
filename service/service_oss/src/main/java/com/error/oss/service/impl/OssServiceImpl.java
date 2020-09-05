package com.error.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.error.oss.service.OssService;
import com.error.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //文件上传至阿里云
            String filename=file.getOriginalFilename();
            //添加随机值
            String uuid=UUID.randomUUID().toString().replaceAll("-","");
            filename=uuid+filename;
            //按日期分类
            String path=new DateTime().toString("yyyy/MM/dd");
            filename=path+"/"+filename;

            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String url="https://"+bucketName+"."+endPoint+"/"+filename;
            return url;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
