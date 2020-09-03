package com.jqc.thread;

public class RunnableImpl implements Runnable {

    private int ticket = 100;

    Object object = new Object();
    public void run() {
        while (true){
            if(ticket>0){
                synchronized (object){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
                    ticket--;
                }
            }
        }

    }
}
