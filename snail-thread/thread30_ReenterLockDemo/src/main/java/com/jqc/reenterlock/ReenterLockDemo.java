package com.jqc.reenterlock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 * 也就说，线程可以进入任何一个它已经拥有的锁同步着的代码块。
 *
 *
 * t1	 invoked sendSMS
 * t1	 ### invoked sendEmail
 * t2	 invoked sendSMS
 * t2	 ### invoked sendEmail
 * +++++++++++++++++++++++++++++++++++++
 * t5	 invoked get
 * t5	 invoked set
 */
class Phone {

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t ### invoked sendEmail");
    }

    Lock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get");
            set();
        }finally{
            lock.unlock();
            lock.unlock();//加几把锁，就关几把锁，不然会一直等待，程序运行不下去
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set");
        }finally{
            lock.unlock();
        }
    }

}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() ->{
            phone.sendSMS();
        },"t1").start();
        new Thread(() ->{
            phone.sendSMS();
        },"t2").start();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        new Thread(() ->{
            phone.get();
        },"t5").start();
    }

}
