import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//创建线程的第三种方式：实现Callable接口，相较于实现Runnbale接口，方法可以有返回值，并且抛出异常
public class TestCallable {

    public static void main(String[] args) {
        ClallbaleDemo clallbaleDemo = new ClallbaleDemo();
        //执行Callable方式，需要FutureTask实现类的支持，用于接收计算结果
        FutureTask<Integer> futureTask = new FutureTask<Integer>(clallbaleDemo);
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();//多个线程抢一个futureTask，只计算一次
        //接受线程运行结果
        try {
            while(!futureTask.isDone()){//没算完一直等着

            }
            //FutureTask可用于闭锁
            //要求获取线程的计算结果，如果计算完成就会去强求，会导致堵塞，直到所有计算都完成，futureTask.get()建议放到最后
            Integer sum = futureTask.get();
            System.out.println("sum："+sum);
            System.out.println("______________________");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class ClallbaleDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
        for (int i = 0; i <= 10000; i++) {
//        for (int i = 0; i <= 100; i++) {
            sum +=i;
            System.out.println(sum);
        }

        return sum;
    }
}