package com.jqc.thread;

public class ThreadRunnableDemo {
    public static void main(String[] args) {
        //创建一个Runnable的接口实现类
        RunnableImpl runnable = new RunnableImpl();
        //创建Thread类对象，构造方法中传递Runnable的实现类对象
        Thread thread = new Thread(runnable);
        thread.start();
        for (int i = 0; i < 20 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
