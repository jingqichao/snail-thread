package com.jqc.thread;

public class ThreadGetName extends Thread {

    @Override
    public void run() {
        String name = getName();
        Thread thread = Thread.currentThread();
        //System.out.println(thread);
        System.out.println(thread.getName());
    }


}
