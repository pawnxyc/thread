package com.thread.countdown;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RanDomCallable implements Callable<Integer> {
    //4.一旦上面的泛型确定了，那么这个重写的方法的返回值类型就是Integer了。
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return new Random().nextInt(10);
    }
    //5.写main方法测试
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //6.创建一个线程对象：
        RanDomCallable ra = new RanDomCallable();

        //7.启动线程，直接用Thread传rdc不行，必须再借助一个FutureTask
        FutureTask<Integer> ft = new FutureTask<Integer>(ra);
        //8.上面已经将线程启动了，直接运行是没有结果的
        new Thread(ft).start();
        //9.我们必须要对返回值处理，那么如何接收返回值：
        Integer i = ft.get();
        //get方法中可以加入sleep,验证get是个阻塞方法
        //10.判断线程是否执行结束：
        System.out.println(ft.isDone());
        System.out.println(i);
        System.out.println(ft.isDone());
    }
}