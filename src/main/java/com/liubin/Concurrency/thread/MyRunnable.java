package com.liubin.Concurrency.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @description 测试任务
 * @author liubin
 * @date 19/12/20 15:01 
 */
@Slf4j
public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            log.info("{} is running", this.name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
