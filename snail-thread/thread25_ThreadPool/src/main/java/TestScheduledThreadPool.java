import java.util.Random;
import java.util.concurrent.*;

public class TestScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        //2.为线程池分配任务
        for (int i = 0; i < 3 ; i++) {
            Future<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);//生产随机数
                    System.out.println(Thread.currentThread().getName()+num);
                    return num;
                }
            },3, TimeUnit.SECONDS);
            System.out.println(result.get());
        }
        //3.关闭线程池
        pool.shutdown();
    }

}