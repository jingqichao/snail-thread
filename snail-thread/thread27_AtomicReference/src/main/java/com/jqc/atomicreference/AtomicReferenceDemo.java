package com.jqc.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        //true	User{userName='li4', age=25}
        //false	User{userName='li4', age=25}
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());

    }

}