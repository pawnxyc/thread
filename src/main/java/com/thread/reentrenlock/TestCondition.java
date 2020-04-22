package com.thread.reentrenlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition{

    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private int count = 0;
    private int takeptr = 0;
    private int putptr = 0;
    Object [] blockArray = new Object[100];

    public static void main(String[] args) {
        final TestCondition condition = new TestCondition();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    condition.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(()->{
                try {
                    condition.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

    public void put (Object element) throws InterruptedException {
        try {
            lock.lock();
            while (count == blockArray.length) {
                System.out.println("put: putptr = " + putptr + ", await");
                full.await();       //等待和唤醒用的不是同一个对象
            }
            System.out.println("put: putptr = " + putptr + ", 执行 put");
            blockArray[putptr] = element;
            if (++putptr == blockArray.length) {
                putptr = 0;
            }
            ++count;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        Object data = null;
        try {
            while (0 == count) {
                System.out.println("take: takeptr == " + takeptr + "，await");
                notFull.await();
            }
            System.out.println("take: takeptr = " + takeptr + ", 执行 take");
            data = blockArray[takeptr];
            if (++takeptr == blockArray.length) {
                takeptr = 0;
            }
            --count;
            full.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}