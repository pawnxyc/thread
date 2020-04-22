package com.thread;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("Thread-pawn") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        t1.start();
        ThreadGroup tg = Thread.currentThread().getThreadGroup();

        Thread[] arrs = new Thread[tg.activeCount()];
        tg.enumerate(arrs);

        Arrays.asList(arrs).forEach(System.out::println);
    }
}
