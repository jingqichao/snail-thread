package com.jqc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

    //CAS:比较并交换，compareAndSet
    public static void main(String[] args) {

        //主物理内存的值
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //多线程拷贝内存的值，进行操作后，回写到主物理内存前进行比较，如果是5，就交换。返回true，且主物理内存的值变为2019
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data"+atomicInteger.get());
        //多线程拷贝内存的值，进行操作后，回写到主物理内存前进行比较，主物理内存的值已经不是5了，返回false
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t current data"+atomicInteger.get());
    }
}
