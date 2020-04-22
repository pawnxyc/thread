package com.thread.concurence;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SellTicket {
    private static Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    static{
        for(int i = 0;i<1000;i++){
            queue.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            new Thread(()->{
                while (true){
                    Integer per = queue.poll();
                    if(per!=null){
                        System.out.println(Thread.currentThread().getName()+"sell ticket"+per);
                    }
                }

            },i+"号线程").start();
        }
    }
}
