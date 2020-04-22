package com.thread.threadApi;

public class JOin {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for(int i = 0;i<10;i++){
            Thread thread = new Thread(new After(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        Thread.sleep(5);
        System.out.println(Thread.currentThread().getName() + "   stop");
    }
    static class After implements Runnable{

        private Thread now;

        After(Thread t1){
            this.now = t1;
        }

        @Override
        public void run() {
            try {
                now.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" stop!");
        }
    }
}
