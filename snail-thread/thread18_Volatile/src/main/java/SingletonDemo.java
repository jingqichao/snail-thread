public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }

    public static SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
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
