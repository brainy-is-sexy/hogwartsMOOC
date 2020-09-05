package com.error.eduservice.controller;

import com.error.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin //解决跨域
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","[admin]").data("avatar","https://imgsa.baidu.com/forum/w%3D580/sign=1f88bfe7b2b7d0a27bc90495fbee760d/a25d8958d109b3de2e3e373dccbf6c81810a4cf2.jpg");//https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
    }
}
