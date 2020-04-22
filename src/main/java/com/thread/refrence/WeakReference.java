package com.thread.refrence;

public class WeakReference {

    public static void main(String[] args) {
        java.lang.ref.WeakReference w = new java.lang.ref.WeakReference(new User());
        System.out.println(w.get());
        System.gc();
        System.out.println(w.get());
        ThreadLocal<User> t1 = new ThreadLocal<>();
        t1.set(new User());
        System.out.println(t1.get());
        System.gc();
        System.out.println(t1.get());
    }
}
