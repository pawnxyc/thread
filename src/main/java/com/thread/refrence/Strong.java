package com.thread.refrence;

public class Strong {
    public static void main(String[] args) {
        User u  = new User("xx");
        u = null;
        System.gc();

    }
}
