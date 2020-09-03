package com.jqc.thread;

public class ThreadSleepDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 60 ; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
