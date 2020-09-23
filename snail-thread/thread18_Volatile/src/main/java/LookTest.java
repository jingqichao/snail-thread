class MyData{
    volatile int number = 0;
    public void add(){
        this.number = 60;
    }

}
public class LookTest {
    //验证volatile的可见性
    public static void main(String[] args) {
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
}

