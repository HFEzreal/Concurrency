package com.liubin.Concurrency.controller;

import com.liubin.Concurrency.threadLocal.ThreadLocalExample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description test
 * @author liubin
 * @date 19/12/27 10:16 
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public long get(){

        return ThreadLocalExample.get();
    }

}
