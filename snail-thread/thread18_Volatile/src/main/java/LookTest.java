import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;
    public void add(){
        this.number = 60;
    }
    public void addPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomicInteger(){
        atomicInteger.getAndIncrement();
    }
}
public class LookTest {
    public static void main(String[] args) {

        noAtomicity();
    }



    //验证volatile的可见性，及时通知其他线程，主物理内存的值已经被修改。
    public static void seeOkByVolatile(){
        MyData myData = new MyData();
        //新起线程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":"+myData.number);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName()+":"+myData.number);
        }).start();

        while (myData.number == 0){
            //如果不加volatile，上面的线程会把主内存的值改为60，而其他线程并不知道它修改了，所以不会执行到这里
            //但是如果加上volatile，其他线程就会知道其他线程把主内存中的值改了，它会重新拷贝最新的主内存的值到它的内存区
            //然后再进行他想要的操作，最后再把结果返回给主内存
        }
        //主线程
        System.out.println(Thread.currentThread().getName()+":"+myData.number);
    }
    //验证volatile不保证原子性，及其使用原子类AtomicInteger的getAndIncrement解决原子性
    public static void noAtomicity() {
        MyData myData = new MyData();
        //seeOkByVolatile();
        for (int i = 0; i < 20 ; i++) {
            new Thread(() ->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlus();
                    myData.addAtomicInteger();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            //使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行。
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int计算结果："+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t atomicInteger计算结果："+myData.atomicInteger);
    }

}

