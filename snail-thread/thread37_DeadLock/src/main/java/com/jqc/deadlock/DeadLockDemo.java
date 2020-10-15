package com.jqc.deadlock;

import java.util.concurrent.TimeUnit;

class LockThread implements Runnable{

    private String lockA;
    private String lockB;

    public LockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockA+"\t 尝试获得锁："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockB+"\t 尝试获得锁："+lockA);
            }
        }
    }
}

/**
 * thread A：	 自己持有锁lockA	 尝试获得锁：lockB
 * thread B：	 自己持有锁lockB	 尝试获得锁：lockA
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new LockThread(lockA,lockB),"thread A：").start();
        new Thread(new LockThread(lockB,lockA),"thread B：").start();
    }
}
