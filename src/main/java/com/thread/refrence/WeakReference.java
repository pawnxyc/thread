package com.thread.refrence;

//弱引用只要发生了gc就会被回收，但前提是只有弱引用，没有强引用
public class WeakReference {

    public static void main(String[] args) {
        ThreadLocal<User> t1 = new ThreadLocal<>();
        t1.set(new User("小黑"));
        Thread t2  = new Thread(()->{
            t1.set(new User("小明"));
            System.out.println(t1.get());
        });
        t2.start();
        System.out.println(t1.get());



    }
}
