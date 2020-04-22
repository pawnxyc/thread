package com.thread.reentrenlock;
//可重入锁比sychronized更灵活，可以设置trylock，指定时间没拿到锁 就放弃等待。或者可被打断属性。
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    private ReentrantLock lock = new ReentrantLock();
    void m() {
        lock.lock();
        System.out.println("m start ");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    void n() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("n start ");
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        Lock l = new Lock();
        new Thread(l::m).start();
        Thread t2 = new Thread(l::n);
        t2.start();
        t2.interrupt();
    }
}
