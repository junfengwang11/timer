package com.kiwi.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangjunfeng
 * @date 2018-09-13
 */
@SpringBootApplication
@EnableScheduling
public class TimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimerApplication.class, args);
    }
}