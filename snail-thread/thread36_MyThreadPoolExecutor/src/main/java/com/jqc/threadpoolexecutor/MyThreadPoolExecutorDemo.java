package com.jqc.threadpoolexecutor;

import java.util.concurrent.*;

public class MyThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //最大线程数5+队列数3=8,10>8所以使用默认的AbortPolicy拒绝策略会直接抛异常
        try{
            for (int i = 0; i < 10 ; i++) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+"\t 自定义线程池");
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        /**
         * java.util.concurrent.RejectedExecutionException: Task com.jqc.threadpoolexecutor.MyThreadPoolExecutorDemo$1@6d6f6e28 rejected from java.util.concurrent.ThreadPoolExecutor@135fbaa4[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
         * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
         * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
         * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
         * 	at com.jqc.threadpoolexecutor.MyThreadPoolExecutorDemo.main(MyThreadPoolExecutorDemo.java:21)
         */
    }
}
