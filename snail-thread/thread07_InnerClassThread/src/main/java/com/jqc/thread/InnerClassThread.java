package com.jqc.thread;

public class InnerClassThread {
    public static void main(String[] args) {

        //父类是Thread
        //匿名内部类重新一个run方法
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i);
                }
            }
        }.start();

        //父类是Runnable
        //Runnable r = new RunnableImpl(); 多态
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i);
                }
            }
        }).start();

    }
}
