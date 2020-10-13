package com.jqc.blockingqueue;

import java.util.Queue;
import java.util.concurrent.*;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {


        //1.阻塞队列API之抛出异常组
        System.out.println("====================1.阻塞队列API之抛出异常组=========================");
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));//队列已满，不可以再加，
//        会报Exception in thread "main" java.lang.IllegalStateException: Queue full异常
        /**
         * true
         * true
         * true
         * Exception in thread "main" java.lang.IllegalStateException: Queue full
         * 	at java.util.AbstractQueue.add(AbstractQueue.java:98)
         * 	at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
         * 	at com.jqc.blockingqueue.BlockingQueueDemo.main(BlockingQueueDemo.java:14)
         */
        blockingQueue.element();//检查队首元素

        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
//        blockingQueue.remove();////队列已空，不可以再删，
//        会报Exception in thread "main" java.util.NoSuchElementException异常
        /**
         * true
         * true
         * true
         * Exception in thread "main" java.util.NoSuchElementException
         * 	at java.util.AbstractQueue.remove(AbstractQueue.java:117)
         * 	at com.jqc.blockingqueue.BlockingQueueDemo.main(BlockingQueueDemo.java:26)
         */
//        LinkedBlockingQueue;
//        SynchronousQueue;
//        LinkedBlockingDeque;
//        LinkedTransferQueue;
        //2.阻塞队列API之返回布尔值组
        System.out.println("====================2.阻塞队列API之返回布尔值组=========================");
        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue1.offer("a"));//插入
        System.out.println(blockingQueue1.offer("b"));
        System.out.println(blockingQueue1.offer("c"));
//        System.out.println(blockingQueue1.offer("d"));//超过三个了，false，但是不抛出异常

        System.out.println(blockingQueue1.peek());//检查

        System.out.println(blockingQueue1.poll());//删除
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
//        System.out.println(blockingQueue1.poll());//超过三个了，null，但是不抛出异常
        //3.阻塞队列API之阻塞和超时控制
        System.out.println("====================3.阻塞队列API之阻塞和超时控制=========================");
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<String>(3);
        blockingQueue2.put("a");
        blockingQueue2.put("b");
        blockingQueue2.put("c");
//        blockingQueue2.put("d");//一直阻塞

        blockingQueue2.take();
        blockingQueue2.take();
        blockingQueue2.take();
//        blockingQueue2.take();//一直阻塞

        //超时控制
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue3.offer("a",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue3.offer("a",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue3.offer("a",2L,TimeUnit.SECONDS));
        System.out.println(blockingQueue3.offer("a",2L,TimeUnit.SECONDS));//阻塞两秒然后返回false

        //4.阻塞队列API之同步SynchronousQueue队列
        System.out.println("====================4.阻塞队列API之同步SynchronousQueue队列=========================");
        BlockingQueue<String> blockingQueue4 = new SynchronousQueue<>();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue4.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue4.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue4.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t "+blockingQueue4.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t "+blockingQueue4.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t "+blockingQueue4.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();


    }
}
