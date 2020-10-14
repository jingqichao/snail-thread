package com.jqc.proandcumblockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent.ArrayBlockingQueue
 * Prod	 生产线程启动
 * Cum	 消费线程启动
 * Prod	 插入对列1成功
 * Cum	 消费对列蛋糕1成功
 * Prod	 插入对列2成功
 * Cum	 消费对列蛋糕2成功
 * Prod	 插入对列3成功
 * Cum	 消费对列蛋糕3成功
 * Prod	 插入对列4成功
 * Cum	 消费对列蛋糕4成功
 * Prod	 插入对列5成功
 * Cum	 消费对列蛋糕5成功
 * 5秒钟时间到，活动停止
 * Prod	 FLAG=false，生产停止
 * Cum	 超过两秒钟没有取到蛋糕，消费退出
 */
class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入对列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入对列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t FLAG=false，生产停止");
    }

    public void myCum() throws InterruptedException {
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);

            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有取到蛋糕，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费对列蛋糕"+result+"成功");
        }
        System.out.println(Thread.currentThread().getName()+"\t FLAG=false，生产停止");
    }

    public void stop(){
        FLAG = false;
    }
}

public class ProductorAndConsumerBlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Prod").start();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myCum();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Cum").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒钟时间到，活动停止");
        myResource.stop();
    }
}
