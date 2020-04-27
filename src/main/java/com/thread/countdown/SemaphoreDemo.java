package com.thread.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    // 排队总人数（请求总数）
    public static int clientTotal = 10;

    // 可同时受理业务的窗口数量（同时并发执行的线程数）
    public static int threadTotal = 2;


    public static void main(String[] args) throws Exception {
//       ExecutorService es = Executors.newCachedThreadPool();//不限制池中的线程数量。
        ExecutorService es = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch count = new CountDownLatch(clientTotal);
        for (int i = 0;i<10;i++){
            final int c = i;
            es.execute(()->{
                try {
                    semaphore.acquire(1);
                    resolve(c);
                    semaphore.release(1);
                    count.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        count.await();
        System.out.println("全部处理完毕");
        es.shutdown();
    }

    private static void resolve(int i) throws InterruptedException {
        System.out.println("服务号{}，受理业务中。。。"+ i);
        Thread.sleep(2000);
        System.out.println("处理结束");
    }
}