import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：在完成某些运算的时候，只有当其他所有线程的运算全部完成，当前运算才会继续执行
 */
public class TestCountDownLatch {

    private CountDownLatch latch;
    public TestCountDownLatch(CountDownLatch latch){
        this.latch = latch;
    }
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 ; i++) {
            new Thread(() ->{
                TestCountDownLatch testCountDownLatch = new TestCountDownLatch(latch);
                for (int j = 0; j < 50000 ; j++) {
                    if (j%2==0){
                        System.out.println(j);
                    }
                }
                //闭锁个数递减1
                latch.countDown();
            },String.valueOf(i)).start();
         }
        try {
            //等待所有计算都完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+ (end-start));
    }
}
