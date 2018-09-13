package com.kiwi.timer.single;

import com.kiwi.timer.util.DateTimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 单线程定时器demo
 *
 * @author wangjunfeng
 * @date 2018-09-13
 */
@Log4j2
@Component
public class SingleDemo {
    public final static long ONE_Minute = 60 * 1000;

    @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        log.info(DateTimeUtil.toDateTimeStr(new Date()) + " >>fixedDelay执行....");
    }

    @Scheduled(fixedRate = ONE_Minute)
    public void fixedRateJob() {
        log.info(DateTimeUtil.toDateTimeStr(new Date()) + " >>fixedRate执行....");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void cronJob() {
        log.info("----" + DateTimeUtil.toDateTimeStr(new Date()) + " >>cron执行....");
    }
}
