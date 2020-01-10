package com.liubin.Concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description 计数器
 * @author liubin
 * @date 20/1/8 17:15 
 */
@Slf4j
public class CyclicBarrierExample {

    private static final int total = 20;
    private static final int client = 5;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(client,()->{
            log.info("await...");
        });
        for (int i = 0; i < total; i++) {
            Thread.sleep(100);
            final int index = i;
            executorService.execute(() -> {
                try {
                    if(index==9){
                        cyclicBarrier.reset();
                    }
                    Thread.sleep(100);
                    log.info("{} ready", index);
                    cyclicBarrier.await(1000,TimeUnit.MILLISECONDS);
                    log.info("{} continue", index);
                } catch (Exception e) {
                    log.error("error", e);
                }
            });
        }
        executorService.shutdown();
    }

    /**
    private int dowait(boolean timed, long nanos)
            throws InterruptedException, BrokenBarrierException,
            TimeoutException {
        // 获取独占锁
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 当前代
            final Generation g = generation;
            // 如果这代损坏了，抛出异常
            if (g.broken)
                throw new BrokenBarrierException();

            // 如果线程中断了，抛出异常
            if (Thread.interrupted()) {
                // 将损坏状态设置为true
                // 并通知其他阻塞在此栅栏上的线程
                breakBarrier();
                throw new InterruptedException();
            }

            // 获取下标
            int index = --count;
            // 如果是 0，说明最后一个线程调用了该方法
            if (index == 0) {  // tripped
                boolean ranAction = false;
                try {
                    final Runnable command = barrierCommand;
                    // 执行栅栏任务
                    if (command != null)
                        command.run();
                    ranAction = true;
                    // 更新一代，将count重置，将generation重置
                    // 唤醒之前等待的线程
                    nextGeneration();
                    return 0;
                } finally {
                    // 如果执行栅栏任务的时候失败了，就将损坏状态设置为true
                    if (!ranAction)
                        breakBarrier();
                }
            }

            // loop until tripped, broken, interrupted, or timed out
            for (;;) {
                try {
                    // 如果没有时间限制，则直接等待，直到被唤醒
                    if (!timed)
                        trip.await();
                        // 如果有时间限制，则等待指定时间
                    else if (nanos > 0L)
                        nanos = trip.awaitNanos(nanos);
                } catch (InterruptedException ie) {
                    // 当前代没有损坏
                    if (g == generation && ! g.broken) {
                        // 让栅栏失效
                        breakBarrier();
                        throw ie;
                    } else {
                        // 上面条件不满足，说明这个线程不是这代的
                        // 就不会影响当前这代栅栏的执行，所以，就打个中断标记
                        Thread.currentThread().interrupt();
                    }
                }

                // 当有任何一个线程中断了，就会调用breakBarrier方法
                // 就会唤醒其他的线程，其他线程醒来后，也要抛出异常
                if (g.broken)
                    throw new BrokenBarrierException();

                // g != generation表示正常换代了，返回当前线程所在栅栏的下标
                // 如果 g == generation，说明还没有换代，那为什么会醒了？
                // 因为一个线程可以使用多个栅栏，当别的栅栏唤醒了这个线程，就会走到这里，所以需要判断是否是当前代。
                // 正是因为这个原因，才需要generation来保证正确。
                if (g != generation)
                    return index;

                // 如果有时间限制，且时间小于等于0，销毁栅栏并抛出异常
                if (timed && nanos <= 0L) {
                    breakBarrier();
                    throw new TimeoutException();
                }
            }
        } finally {
            // 释放独占锁
            lock.unlock();
        }
    }
     **/

}
