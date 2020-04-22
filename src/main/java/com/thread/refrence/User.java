package com.thread.refrence;

public class User {
    private String name;

//    一旦该对象被gc回收，则调用此方法。
    @Override
    public void finalize(){
        System.out.println("被gc回收了");
    }

}
