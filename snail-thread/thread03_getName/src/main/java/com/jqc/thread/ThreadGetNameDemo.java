package com.jqc.thread;

public class ThreadGetNameDemo {
    public static void main(String[] args) {
        ThreadGetName threadGetName = new ThreadGetName();
        threadGetName.start();
        new ThreadGetName().start();
        new ThreadGetName().start();
        //获取主线程的名字
        System.out.println(Thread.currentThread().getName());
    }
}
