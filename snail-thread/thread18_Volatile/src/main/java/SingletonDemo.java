public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }

    //DCL（Double Check Lock双端检锁机制），不一定线程安全，原因是有指令重排的问题
    public static SingletonDemo getInstance(){
        if (instance == null){
            //在方法内部加同步代码块
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        //单线程，单例模式没有问题
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        //多线程，
        for (int i = 0; i < 10 ; i++) {
            new Thread(() ->{
                //0构造方法
                //1构造方法
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
         }
    }
}
