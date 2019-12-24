package com.liubin.Concurrency.thread;

import java.time.LocalDateTime;

/**
 * @description 测试任务
 * @author liubin
 * @date 19/12/20 15:01 
 */
public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(LocalDateTime.now().toString()+"\t"+this.name + " is running");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
