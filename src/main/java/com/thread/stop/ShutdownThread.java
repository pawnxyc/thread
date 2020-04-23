package com.thread.stop;
//优雅的结束线程。
public class ShutdownThread {
    private Thread serverThread;
    private boolean flag = false;
    public void excute(Runnable task){
        serverThread = new Thread(()->{
            Thread t1 = new Thread(task);
            t1.setDaemon(true);
            t1.start();
            try {
                t1.join();
                flag = true;//线程执行完成。
            } catch (InterruptedException e) {
            }
        });
        serverThread.start();

    }
    public void shutdown(long mills){
        long current = System.currentTimeMillis();
        try {
        while (!flag){
            if(System.currentTimeMillis()-current>=mills){
                System.out.println("线程执行超时，结束线程");
                serverThread.interrupt();
                break;
            }
        }

            serverThread.sleep(1);
            System.out.println("还没超时");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ShutdownThread st = new ShutdownThread();
        long mill = System.currentTimeMillis();
        st.excute(()->{
            System.out.println("start read a very big file!");
            for(;;);
        });
        st.shutdown(10000);
        System.out.println(System.currentTimeMillis()-mill);
    }
}
