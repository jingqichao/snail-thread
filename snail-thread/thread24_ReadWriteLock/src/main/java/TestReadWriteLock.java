import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();
        new Thread(() ->{
            rw.get();
         },"write").start();
        for (int i = 0; i < 100 ; i++) {
            new Thread(() ->{
                rw.set(100);
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
            System.out.println(Thread.currentThread().getName()+"读");
        }finally {
            lock.readLock().unlock();
        }
    }
    public void set(int number){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"写");
            this.number = number;
        }finally {
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