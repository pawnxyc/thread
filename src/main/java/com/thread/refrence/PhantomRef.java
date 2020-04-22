package com.thread.refrence;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
//一个线程不断往里面装数据 让gc开始工作。一个负责监听。
public class PhantomRef {
    private static LinkedList<byte[]> list = new LinkedList<>();
    private static ReferenceQueue<User> QUEUE = new ReferenceQueue();
    public static void main(String[] args) {

        PhantomReference<User> p = new PhantomReference<>(new User(),QUEUE);

        new Thread(()->{
            while (true) {
                list.add(new byte[1024 * 1024 * 3]);
                try {
                    System.out.println(p.get());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            Reference<? extends User> pool = QUEUE.poll();
            while (true) {
                if (pool !=null){
                    System.out.println("被gc回收了" + pool);
                }
                }
        }).start();
    }
}
