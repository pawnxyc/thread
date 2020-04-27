package com.thread.countdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//一个银行只有3个柜台，然后有10个人要去办理业务。
public class Bank {
    private static volatile int WORKER= 3;

    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0;i<10;i++){
            ex.execute(()->{
                while (true){
                    while (WORKER > 0) {
                        WORKER--;
                        System.out.println(Thread.currentThread().getName() + "正在办理业务");
                        try {
                            Thread.sleep(5000);
                            System.out.println(Thread.currentThread().getName() + "finished");
                            WORKER++;
                            break;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }
}
