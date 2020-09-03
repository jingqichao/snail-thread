package thread;

public class RunnableImpl implements Runnable {

    private int ticket = 100;

    //Object object = new Object();
    public void run() {
        while (true){
            if(ticket>0){
                payTicket();
            }
        }

    }
    //同步方法解决线程安全问题，这里的锁就是调用方法的对象本身，this
    public synchronized void payTicket(){
        System.out.println(this);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ticket--;
        System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");

    }
}
