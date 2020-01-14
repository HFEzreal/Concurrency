package com.liubin.Concurrency.thread;

import java.util.concurrent.*;

/**
 * @description 拒绝策略
 * @author liubin
 * @date 19/12/20 14:42 
 */
public class RejectedExecutionHandlerTest {

    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {
        //新建一个线程池，corePoolSize和maximumPoolSize都是1，ArrayBlockingQueue是有界队列，容量设置为1
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(CAPACITY));
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        //设置拒绝策略
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//直接丢弃当前任务，并抛出RejectedExecutionException异常，默认
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//直接丢弃当前任务
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());//丢弃队列里面最前面的任务，然后重新尝试执行任务（重复此过程）
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程处理该任务

        //新建10个任务，把他们放到线程中
        for(int i=0;i<10;i++){
            Runnable myrun = new MyRunnable("task-"+i);
            pool.execute(myrun);
        }
        //关闭线程
        pool.shutdown();
    }

}
