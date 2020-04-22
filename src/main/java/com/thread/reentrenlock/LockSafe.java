package com.thread.reentrenlock;

import java.util.concurrent.locks.ReentrantLock;

//利用公平锁实现交替执行
public class LockSafe {
    private static volatile int i = 0;
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (i<26){
                lock.lock();
                System.out.println(i);
                lock.unlock();
            }
        });
        Thread t2 = new Thread(()->{
            while (i<26){
                lock.lock();
                char s = (char)('A' + i++);
                System.out.println(s);
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
