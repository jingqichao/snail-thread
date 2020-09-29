import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//创建线程的第三种方式：实现Callable接口，相较于实现Runnbale接口，方法可以有返回值，并且抛出异常
public class TestCallable {

    public static void main(String[] args) {
        ClallbaleDemo clallbaleDemo = new ClallbaleDemo();
        //执行Callable方式，需要FutureTask实现类的支持，用于接收计算结果
        FutureTask<Integer> futureTask = new FutureTask<Integer>(clallbaleDemo);
        new Thread(futureTask).start();
        //接受线程运行结果
        try {
            Integer sum = futureTask.get();//FutureTask可用于闭锁
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