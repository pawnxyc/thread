package com.thread.cacheline;
//这个地方加不加static效果都一样，因为catchline的最小单位就是64个字节，然后volatile也是以catchline
//为单位去保证共享变量可见性的，一个数组的元素物理地址一般是连续的，所以每次t1改了值都会刷新到内存中去，以保证可见性

public class PaddingLine1 {

    static class Obj{
        volatile public long l1 = 0l;
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
