package com.error.eduservice.client;

import com.error.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class) //调用的服务名称
@Component
public interface VodClient {

    //定义调用的方法路径
    //根据视频id删除阿里云视频
    //@PathVariable注解一定要指定参数名称，否则出错
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
