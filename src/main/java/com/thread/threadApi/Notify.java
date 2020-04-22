package com.thread.threadApi;

public class Notify {
    private static boolean flag = false;
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        final Thread waitThread = new Thread(new WaitThread(), "waitThread");
        final Thread notifyThread = new Thread(new NotifyThread(), "notifyThread");
        waitThread.start();
        Thread.sleep(5);
        notifyThread.start();
        Thread.sleep(5);
    }
//    等待flag变化时执行一些业务逻辑。
    static class WaitThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                while (!flag) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"当前wait线程等待中");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("wait线程执行业务" + Thread.currentThread().getName());
            }
        }
    }
    static class NotifyThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                flag = true;
                lock.notifyAll();
                System.out.println("notify线程执行,通知wait可以执行了" + Thread.currentThread().getName());
            }
            synchronized (lock) {

                System.out.println("notify重写竞争到锁" + Thread.currentThread().getName());
            }
        }
    }

}
