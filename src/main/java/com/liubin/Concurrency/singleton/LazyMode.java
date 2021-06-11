package com.liubin.Concurrency.singleton;

import java.io.Serializable;

/**
 * @description 懒汉模式
 * @author liubin
 * @date 19/12/24 17:54
 */
public class LazyMode implements Serializable {

    private String name;
    private int code;

    public LazyMode() {
        this.code = 1;
        this.name = "懒汉";
    }

    private static LazyMode instance;
    private static volatile LazyMode volatileInstance;

    //添加简单的判断：线程不安全
    public static LazyMode getNotNullInstance() {
        if (instance == null) {
            instance = new LazyMode();
        }
        return instance;
    }

    //使用sync修饰代码块：线程安全
    public synchronized static LazyMode getSyncInstance() {
        if (instance == null) {
            instance = new LazyMode();
        }
        return instance;
    }

    //双重检测：线程不安全（指令重排序导致的线程不安全）

    /**
     * instance = new LazyMode(); JVM做了三步操作
     * memory = allocate(); 1.分配对象的内存空间
     * ctroInstance(); 2.初始化对象
     * instance = memory 3. 设置instance指向刚分配的内存
     *
     * 发生指令重排后
     *
     * memory = allocate(); 1.分配对象的内存空间
     * instance = memory 3. 设置instance指向刚分配的内存
     */
    public static LazyMode doubleCheck() {
        if (instance == null) {
            synchronized (LazyMode.class) {
                instance = new LazyMode();
            }
        }
        return instance;
    }

    //双重检测+volatile修饰：线程安全
    public static LazyMode doubleCheckAndVolatile() {
        if (volatileInstance == null) {
            synchronized (LazyMode.class) {
                volatileInstance = new LazyMode();
            }
        }
        return volatileInstance;
    }

    // 防止序列化破坏单例模式
    private Object readResolve() {
        return volatileInstance;
    }
}
