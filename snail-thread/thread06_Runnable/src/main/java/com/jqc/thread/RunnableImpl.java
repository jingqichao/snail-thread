package com.jqc.thread;

public class RunnableImpl implements Runnable {
    public void run() {
        for (int i = 0; i < 20 ; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
