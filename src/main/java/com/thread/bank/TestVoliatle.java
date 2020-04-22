package com.thread.bank;
//如果不加volatile会有一定记录出现死循环。线程t1一直不从内存中重新读取running
public class TestVoliatle {
     boolean running = true;
//    volatile boolean running = true;
    void m(){
        System.out.println("线程开始执行");
        while(running){
        }
        System.out.println("线程执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        TestVoliatle t = new TestVoliatle();
        Thread t1 = new Thread(t::m,"running");
//        t1.setDaemon(true);
        t1.start();
        Thread.sleep(1);
        t.running = false;
    }
}
