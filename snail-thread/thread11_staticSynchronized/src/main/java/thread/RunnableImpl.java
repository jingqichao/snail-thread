package thread;

public class RunnableImpl implements Runnable {

    private static int ticket = 100;

    public void run() {
        while (true){
            if(ticket>0){
                payTicket();
            }
        }
    }
    //静态的同步方法解决线程安全问题，这里的锁对象是class
    public static /**synchronized*/ void payTicket(){
        //System.out.println(RunnableImpl.class);
        synchronized (RunnableImpl.class){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
            ticket--;
        }
    }
}
