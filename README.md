# hogwarts-MOOC
采用Springboot+Vue搭建的在线教育视频网站。采用B2C商业模块，使用微服务架构，项目采用前后端分离开发。项目分为前台系统和后台系统。

前台系统包含：首页数据显示、课程列表和详情、课程支付、课程视频播放、微信登陆、微信支付等

[前台系统前端代码地址](https://github.com/brainy-is-sexy/hogwarts-FrontView)

后台系统包含：权限管理、教师管理、课程分类管理、课程管理、统计分析等

[后台系统前端代码地址](https://github.com/brainy-is-sexy/hogwarts-FrontManage)



### 项目主要技术

##### 前端：Node + Vue +element-ui+NUXT

##### 后端：SpringBoot + SpringCloud +Redis + Nginx + MySQL + Maven

##### 其他涉及到的中间件包括Redis、阿里云OSS、阿里云视频点播



### 实现效果

#### 前台系统

![data](https://github.com/brainy-is-sexy/hogwartsMOOC/blob/master/qian.png)

#### 后台系统

![q](https://github.com/brainy-is-sexy/hogwartsMOOC/blob/master/hou.png)


### 项目部署

由于后端采用微服务架构，需要先启动微服务配置中心nacso，以及redis

[nacso下载地址](https://github.com/alibaba/nacos/releases)

[redis下载地址](https://github.com/MicrosoftArchive/redis/releases)

一共有10个服务全部启动

![](https://github.com/brainy-is-sexy/hogwartsMOOC/blob/master/1.png)

api_gateway:网关服务

service_acl:权限管理

service_cms:轮播图服务

service_edu:教师课程（主要服务）

service_msm:短信发送服务

service_order:订单服务

service_oss:阿里云oss服务

service_statistics:统计图表服务

service_ucenter:登陆注册服务

service_vod:视频播放服务



##### 至此，项目后端启动完毕。前端启动请参考：

[前台系统前端代码地址](https://github.com/brainy-is-sexy/hogwarts-FrontView)

[后台系统前端代码地址](https://github.com/brainy-is-sexy/hogwarts-FrontManage)
