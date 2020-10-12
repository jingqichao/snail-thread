package com.jqc.copyonwritearraylist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArrayListDemo {
    /**
     * 一，故障现象
     * 多线程并发操作ArrayList.
     * java.util.ConcurrentModificationException并发修改异常
     * 二，导致原因
     * 多线程并发操作ArrayList时，争抢修改,导致的异常
     * 三，解决方案
     * ①new vector,底层syncronized。但是并发性严重下降
     * ②Collections.syncronizedList(new ArrayList<>());进行安全封装
     * ③new CopyOnWriteArrayList<>();
     * 原理：
     * 写时复制，读写分离的思想.
     * CopyOnWrite容器即写时复制的容器。向一个容器添加元素的时候，不直接往当前容器object[]添加，
     * 而是先将当前容器object[]进行copy复制出一个新的容器object[] newElements,
     * 然后新的容器object[] newElements里添加元素，添加完元素之后。
     * 再将原容器的引用指向新的容器setArray(newElements).这样做的好处是，
     * 可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
//        set.add("a");
        //Exception in thread "0" java.util.ConcurrentModificationException
        for (int i = 0; i < 40 ; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
