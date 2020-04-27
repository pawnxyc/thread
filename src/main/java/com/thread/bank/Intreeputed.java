package com.thread.bank;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Intreeputed {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("i meet some error, will exist.");
        }));
        Thread t1 = new Thread(()-> {
            try {
                Thread.sleep(100_000);
            }catch (InterruptedException e){
                System.out.println(Thread.interrupted());
                e.printStackTrace();
            }
        },"cc");
        Thread t2 = new Thread(()-> {

                System.out.println(">>>"+Thread.currentThread().isInterrupted());

        },"cc");

        t2.start();
        t2.join();
        t1.start();
        t1.interrupt();
        System.out.println(t1.isInterrupted());
    }
}
