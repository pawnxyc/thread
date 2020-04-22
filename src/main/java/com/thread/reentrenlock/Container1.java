package com.thread.reentrenlock;

import java.util.LinkedList;

//面试题。写一个固定容量的同步容器。支持10个消费者阻塞get。支持两个生产者阻塞put().
public class Container1<T> {
    private LinkedList<T> list = new LinkedList<>();
    private static final int MAX = 10;
    synchronized void put(T t) throws InterruptedException {
        while (list.size() == MAX){
            this.wait();
        }
        list.add(t);
        this.notifyAll();
    }
    synchronized T get() throws InterruptedException {
        while (list.size()==0){
            this.wait();
        }
        T t = list.removeFirst();
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        Container1<String> container1 = new Container1();
        for(int i = 1;i<=10;i++){
            new Thread(()->{
                try {
                    System.out.println(container1.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"custome"+i).start();
        }

        for(int i = 1;i<=2;i++){
            new Thread(()->{
                try {
                    container1.put("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"product"+i).start();
        }
    }
}
