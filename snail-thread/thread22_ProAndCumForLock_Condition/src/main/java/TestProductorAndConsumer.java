import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者案例：使用lock锁中的Condition线程通信的等待唤醒机制
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
        //虚假唤醒问题出现
        new Thread(productor,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}

//店员
class Clerk{
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //进货
    public void get(){
        lock.lock();
        try {
            while (product>=10){
                System.out.println("产品已满");
                try {
                    //this.wait();  使用lock锁中的Condition线程通信的等待唤醒机制
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ ++product);
            //this.notify();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //卖货
    public synchronized void sale(){
        lock.lock();
        try{
            while (product<=0){
                System.out.println("缺货");
                try {
//                this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
//        this.notify();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

//生产者
class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

//消费者
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20 ; i++) {
            clerk.sale();
        }
    }
}