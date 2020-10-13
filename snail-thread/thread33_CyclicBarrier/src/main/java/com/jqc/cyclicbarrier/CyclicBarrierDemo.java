package com.jqc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier（栅栏） 之所以叫barrier，
 * 是因为是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点时被阻塞 。
 * 直到最后一个线程到达屏障点时，屏障才会打开。所以拦截的屏障才会继续干活。
 * 线程进入屏障使用CyclicBarrier的await()方法。
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("========7颗集齐了");
        });
        for (int i = 0; i < 7 ; i++) {
            final int temInt = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+temInt+"颗");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
         }
    }
}
