package com.jqc.thread;

public class ThreadSetNameDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start();
        //调用重写后的父类的构造方法，传递自定义的线程名字给父类
        new MyThread("fatherThread").start();
    }
}
