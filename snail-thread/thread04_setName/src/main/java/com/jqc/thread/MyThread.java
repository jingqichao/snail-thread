package com.jqc.thread;

public class MyThread extends Thread {
    public MyThread() {

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    //父类的构造方法
    public MyThread(String name) {
        super(name);
    }
}
