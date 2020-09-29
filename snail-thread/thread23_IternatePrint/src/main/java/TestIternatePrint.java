import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//编写一个程序，开始三个线程，这三个线程的ID分别为A,B,C；没个线程将自己的ID打印在屏幕上，打印10遍。要求输出结果为AABBCC...依次递归
//线程按序交替打印
public class TestIternatePrint {
    public static void main(String[] args) {
        IternateDemo iternateDemo = new IternateDemo();
        //A线程
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                iternateDemo.loopA(i);
            }
        },"A").start();

        //B线程
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                iternateDemo.loopB(i);
            }
        },"B").start();

        //C线程
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                iternateDemo.loopC(i);
            }
        },"C").start();
    }
}

//
class IternateDemo {
    private static int number = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void loopA(int total) {
        lock.lock();
        try {
            //1.判断线程1是否满足条件
            if (number != 1) {
                condition1.await();
            }
            //2.否则打印
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"第"+total+"次循环！");
            }
            //3.唤醒线程2
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int total) {
        lock.lock();
        try {
            //1.判断线程1是否满足条件
            if (number != 2) {
                condition1.await();
            }
            //2.否则打印
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"第"+total+"次循环！");
            }
            //3.唤醒线程2
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int total) {
        lock.lock();
        try {
            //1.判断线程1是否满足条件
            if (number != 3) {
                condition1.await();
            }
            //2.否则打印
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+"第"+total+"次循环！");
            }
            System.out.println("-------------------------------");
            //3.唤醒线程2
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
