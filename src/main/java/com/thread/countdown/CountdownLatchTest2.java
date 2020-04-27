package com.thread.countdown;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//相当于是一个计数器。
public class CountdownLatchTest2 {
        private static final LinkedList<String> list= new LinkedList<>();
        public static void main(String[] args) {
            ExecutorService service = Executors.newCachedThreadPool();
            final CountDownLatch cdOrder = new CountDownLatch(1);
            final CountDownLatch cdAnswer = new CountDownLatch(4);
            for (int i = 0; i < 4; i++) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                            cdOrder.await();
                            System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                            long mill = System.currentTimeMillis();
                            Thread.sleep((long) (Math.random() * 10000));
                            list.addLast(Thread.currentThread().getName()+"用时："+(System.currentTimeMillis()-mill));
                            cdAnswer.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                service.execute(runnable);
            }
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("裁判"+Thread.currentThread().getName()+"即将发布口令");
                System.out.println(Thread.currentThread().getStackTrace());
                cdOrder.countDown();
                System.out.println("裁判"+Thread.currentThread().getName()+"已发送口令，正在等待所有选手到达终点");
                cdAnswer.await();
                System.out.println("所有选手都到达终点");
                System.out.println("裁判"+Thread.currentThread().getName()+"汇总成绩排名");
                list.forEach(e-> System.out.println(e));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.shutdown();
        }
    }
