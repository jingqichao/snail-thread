package com.jqc.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *自旋锁：是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式尝试获取锁，
 * 这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU。
 * A	 myLock
 * B	 myLock
 * A	 myUnLock
 * B	 myUnLock
 * B线程会自旋等待A线程用完锁之后，B才会自旋判断这个锁是否释放了，然后才执行自己的释放锁
 */
public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> reference = new AtomicReference<>();//默认为null
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t myLock");
        while (!reference.compareAndSet(null,thread)){//默认值和期望值相同，update，结果返回true

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t myUnLock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"A").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() ->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"B").start();
    }
}
