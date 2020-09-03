package com.jqc.thread;

import com.jqc.thread.RunnableImpl;

public class ThreadSafeDemo {
    public static void main(String[] args) {
        RunnableImpl runnable = new RunnableImpl();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
