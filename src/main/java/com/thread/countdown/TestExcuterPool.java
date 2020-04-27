package com.thread.countdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class TestExcuterPool {
    private static class Print implements Callable<String>{
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName()+ "hello world";
            return name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        ArrayList<Future<String>> list = new ArrayList<>();
        Arrays.asList(1,2,3,4,5).forEach((s)->{
            Future<String> submit = es.submit(new Print());
            list.add(submit);
        });
        Thread.sleep(3000);

        list.forEach((s)-> {
            try {
                System.out.println(s.get() + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        es.shutdownNow();
    }
}
