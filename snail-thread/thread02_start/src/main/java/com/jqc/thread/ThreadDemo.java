package com.jqc.thread;

public class ThreadDemo{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        for (int i = 0; i < 20 ; i++) {
            System.out.println("main"+i);
        }
    }
}
