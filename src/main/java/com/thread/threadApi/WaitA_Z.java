package com.thread.threadApi;

public class WaitA_Z {

    private volatile static Integer i  = 0;
    private static volatile Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (lock) {
                while (i < 26) {
                    System.out.println(++i);
                    lock.notify();
                    try {
                        lock.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (lock) {
                while (i < 26) {
                    char c1 = new Character('A');
                    char c2 = ((char) (c1 + i));
                    System.out.println(c2);
                    lock.notify();
                    try {
                        lock.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        t2.start();
        t1.start();
    }
}
