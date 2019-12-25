package com.liubin.Concurrency.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 对象溢出
 * @author liubin
 * @date 19/12/24 16:08 
 */
@Slf4j
public class ObjectOverflow {

    private String test;
    private ObjectOverflow(){
        new InnerClass();//构造函数尚未执行完，对象没有正确的发布
        this.test = "123";
        log.info("constructor method");
    }


    static {
        log.info("static block");
    }

    static void method() {
        log.info("static method");
    }

    //内部类
    private class InnerClass{
        private InnerClass(){
            log.info(ObjectOverflow.this.test);//这里拿到的值有可能不是正确的对象值
        }
    }

    public static void main(String[] args) {
        new ObjectOverflow();
    }
}
