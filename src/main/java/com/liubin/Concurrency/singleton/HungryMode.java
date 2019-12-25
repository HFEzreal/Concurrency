package com.liubin.Concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 饿汉模式
 * @author liubin
 * @date 19/12/24 17:55 
 */
@Slf4j
public class HungryMode {

    private static HungryMode instance = new HungryMode();

    //线程安全
    public static HungryMode getInstance() {
        return instance;
    }

    //注意静态代码块的顺序，会影响执行结果
    public static HungryMode getInstanceNull() {
        return instanceNull;
    }
    //1
    static {
        instanceNull = new HungryMode();
    }
    //2
    private static HungryMode instanceNull = null;


    public static void main(String[] args) {
        log.info(HungryMode.getInstance().toString());
        log.info(HungryMode.getInstanceNull().toString());
    }

}
