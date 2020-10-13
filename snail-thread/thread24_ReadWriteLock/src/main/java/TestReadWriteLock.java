import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 独占锁（写锁）:指该锁一次只能被一个线程锁持有。ReentrantLock和synchronized都是独占锁
 * 共享锁（读锁）:指该锁可被多个线程锁持有。
 * 对于ReentrantReadWriteLock其读锁时共享锁，其写锁时独占锁
 * 读锁的共享锁可保证并发读是非常高效的，读写，写读，写写的过程都是互质的。
 * read正在写
 * read写完了
 * read正在写
 * read写完了
 * write：正在读
 * write：读完了
 * read正在写
 * read写完了
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();
        for (int i = 0; i < 20 ; i++) {
            new Thread(() ->{
                rw.get();
            },"write：").start();
         }
        for (int i = 0; i < 20 ; i++) {
            new Thread(() ->{
                rw.set(10);
            },"read").start();
         }
    }
}
//读写锁：有写的时候，需要互斥
class ReadWriteLockDemo{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在读");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"读完了");
            lock.readLock().unlock();
        }
    }
    public void set(int number){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在写");
            this.number = number;
        }finally {
            System.out.println(Thread.currentThread().getName()+"写完了");
            lock.writeLock().unlock();
        }
    }
}
//D:\Java\jdk1.8.0_211\bin\java.exe "-javaagent:D:\idea\IntelliJ IDEA 2019.3.1\lib\idea_rt.jar=2000:D:\idea\IntelliJ IDEA 2019.3.1\bin" -Dfile.encoding=UTF-8 -classpath D:\Java\jdk1.8.0_211\jre\lib\charsets.jar;D:\Java\jdk1.8.0_211\jre\lib\deploy.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\access-bridge-64.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\cldrdata.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\dnsns.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\jaccess.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\jfxrt.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\localedata.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\nashorn.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\sunec.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\sunjce_provider.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\sunmscapi.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\sunpkcs11.jar;D:\Java\jdk1.8.0_211\jre\lib\ext\zipfs.jar;D:\Java\jdk1.8.0_211\jre\lib\javaws.jar;D:\Java\jdk1.8.0_211\jre\lib\jce.jar;D:\Java\jdk1.8.0_211\jre\lib\jfr.jar;D:\Java\jdk1.8.0_211\jre\lib\jfxswt.jar;D:\Java\jdk1.8.0_211\jre\lib\jsse.jar;D:\Java\jdk1.8.0_211\jre\lib\management-agent.jar;D:\Java\jdk1.8.0_211\jre\lib\plugin.jar;D:\Java\jdk1.8.0_211\jre\lib\resources.jar;D:\Java\jdk1.8.0_211\jre\lib\rt.jar;D:\git-thread\snail-thread\thread24_ReadWriteLock\target\classes TestReadWriteLock
//read写
//write读
//read写
//read写
//read写
//read写
//read写