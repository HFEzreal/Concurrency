package com.liubin.Concurrency.deadlock;

/**
 * @author liubin
 * @description 死锁
 * @date 20/6/6 14:44
 */
public class LockTest {

    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();
        Thread threadA = new Thread(() -> {
            synchronized (a){
                System.out.println("thread A lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println("thread B lock");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (b){
                System.out.println("thread B lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println("thread A lock");
                }
            }
        });

        threadA.start();
        threadB.start();
        // 死锁检测命令
        //1. jps 列出所有java进程的pid
        //2. jstack pid 根据pid打印线程栈，查看死锁
    }
}