import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {

    public static void main(String[] args) {

        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        TestThreadPoolDemo demo = new TestThreadPoolDemo();
        //2.为线程池分配任务
        for (int i = 0; i < 10 ; i++) {
            pool.submit(demo);
        }
        //3.关闭线程池
        pool.shutdown();
    }

}

class TestThreadPoolDemo implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}