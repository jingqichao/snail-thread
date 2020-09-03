package com.jqc.thread;

public class ThreadDemoMain {
    public static void main(String[] args) {
        Person p1 = new Person("小强");
        Person p2 = new Person("旺财");
        p1.run();
        //System.out.println(0/0);后续线程失败
        p2.run();
    }
}
