package com.jqc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 0; i < 10 ; i++) {//模拟6部汽车
            new Thread(() ->{
                try {
                    semaphore.acquire();//获取信号量的许可
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();//释放它所持有的信号量许可
                }
            },String.valueOf(i)).start();
        }
    }
}
