package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableImpl implements Runnable {

    private static int ticket = 100;
    //解决线程安全的第三张方法：增加Lock锁，实现ReentrantLock方法
    Lock lock = new ReentrantLock();
    public void run() {
        while (true){
            lock.lock();//开启锁
            if(ticket>0){
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
                    ticket--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    lock.unlock();//无论程序是否出现异常都释放这个锁
                }

            }
        }
    }
}
