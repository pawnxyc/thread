package com.thread.deamon;

public class Deamon {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread InnerThread = new Thread(()->{
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            InnerThread.setDaemon(true);
            InnerThread.start();
        });

        t.start();
    }
}
