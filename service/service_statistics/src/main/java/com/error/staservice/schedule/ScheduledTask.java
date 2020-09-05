package com.error.staservice.schedule;

import com.error.staservice.service.StatisticsDailyService;
import com.error.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;

    // 0/5 * * * * ?表示每隔5秒执行一次这个方法
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        System.out.println("**************task1执行了..");
    }

    //在每天凌晨1点，把前一天数据进行数据查询添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
