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

        //++
        //    public final int getAndIncrement() {
        //        return unsafe.getAndAddInt(this, valueOffset, 1);
        //    }
        //this  当前对象
        //valueOffset   内存偏移量
        //rt.jar\sun\misc
        //Unsafe类
        //CAS的全称为Compare And Swap，它是一条CPU并发原语。
        // 它的功能是判断内存某个位置的值是否为预期值，如果是则更新为新的值，这个过程是原子性的。
        //CAS并发原语体现在Java语言中就是sun.misc.Unsafe类中的各个方法。调用Unsafe类中的CAS方法，
        // JVM会帮我我们实现出CAS汇编指令。这是一种完全依赖硬件的功能，通过它实现了原子操作。
        // 由于CAS是一种系统原语，原语属于操作系统用于范涛，是由若干指令组成的，用于完成某个功能的一个过程，
        // 并且原语的执行是必须连续的，在执行过程中不允许被中断，也就是说CAS是一条CPU的原子指令，不会造成所谓的数据不一致问题。

        //底层原理：
        //Unsafe类是CAS的核心类，由于java方法无法直接访问底层系统，需要通过本地（native）方法来访问，Unsafe相当于一个后门，
        // 基于该类可以直接操作特定内存的数据。Unsafe类存在于sun.misc包中，其内部方法操作可以像C的指针一样直接操作内存，
        // 因为java中CAS操作的执行依赖于Unsafe类的方法。
        //Unsafe类中的所有方法都是native修饰的，也就是Unsafe类中的方法都是直接调用操作系统底层资源执行相应的任务
        //Unsafe类中变量valueOffset：表示该变量值在内存中的偏移量，因为Unsafe就是根据内存偏移地址来获取数据的
        //Unsafe类中value：用volatile保证了多线程之间的内存可见性
        //自旋锁思想
        //    public final int getAndAddInt(Object var1, long var2, int var4) {
        //        int var5;
        //        do {
        //            var5 = this.getIntVolatile(var1, var2);
        //        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        //
        //        return var5;
        //    }

        ////UnSafe类中
        //	// setup to use Unsafe.compareAndSwapInt for updates
        //    private static final Unsafe unsafe = Unsafe.getUnsafe();
        //    private static final long valueOffset;
        //
        //    static {
        //        try {
        //            valueOffset = unsafe.objectFieldOffset
        //                (AtomicInteger.class.getDeclaredField("value"));
        //        } catch (Exception ex) { throw new Error(ex); }
        //    }
        //
        //    private volatile int value;
        atomicInteger.getAndIncrement();



    }
}
