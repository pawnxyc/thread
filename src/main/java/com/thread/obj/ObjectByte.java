package com.thread.obj;

import org.openjdk.jol.info.ClassLayout;

public class ObjectByte {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable(o));
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable(o));
        }
        new Thread(()->{
        synchronized (o){
            System.out.println("=========");
            System.out.println(ClassLayout.parseInstance(o).toPrintable(o));
        }
        }
        ).start();
    }
}
