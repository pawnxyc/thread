package com.thread.cacheline;

public class PaddingLine2 {
//前面7个long，后面7个long，不管跟前面一起到catchline还是后面，都能组成64个字节
    static class Obj{
        volatile public long l0,l2,l3,l4,l5,l6,l7;
        volatile public long l1 = 0l;
        volatile public long p1,p2,p3,p4,p5,p6,p7;
    }

    static Obj[] arr1 = new Obj[2];

    public static void main(String[] args) throws InterruptedException {
        arr1[0] = new Obj();
        arr1[1] = new Obj();
        Thread t1 = new Thread(()->{
            for (long i = 1;i< 1000_0000l;i++){
                arr1[0].l1 = i;
            }
        });
        Thread t2 = new Thread(()->{
            for (long i = 1;i< 1000_0000l;i++){
                arr1[1].l1 = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/10_0000);//毫米
    }
}
