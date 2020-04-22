package com.thread.refrence;

import java.lang.ref.SoftReference;
//为此程序只分配了-Xmx20m的最大堆内存。所以当内存快要溢出时，将软引用gc掉。
public class SoftRefrence {
    public static void main(String[] args) {
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(soft.get());
        byte[] br = new byte[1024*1024*12];
        System.out.println(soft.get());
    }
}
