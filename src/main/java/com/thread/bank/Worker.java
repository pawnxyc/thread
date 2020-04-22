package com.thread.bank;

public class Worker implements Runnable{

    private static final int MAX =50;

    private static int index = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (index <= MAX) {

                    System.out.println(Thread.currentThread() + " 拿到的号码 " + index);
                    index++;
                }
            }
    }

    public static void main(String[] args) {
        final Worker w = new Worker();
        Thread w1 = new Thread(w,"");
        w1.setName("柜员1");
        Thread w2 = new Thread(w);
        w2.setName("柜员2");
        Thread w3 = new Thread(w);
        w3.setName("柜员3");
        w1.start();
        w2.start();
        w3.start();
    }
}
