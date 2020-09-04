package thread;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

public class WaitAndNotifyDemo {

    public static void main(String[] args) {
        final Object object = new Object();
        //消费者
        new Thread() {
            @Override
            public void run() {
                    synchronized (object) {
                        System.out.println("1.老板，我要买包子");
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("3.吃包子");
                        System.out.println("-----------------------");
                    }
                }
        }.start();
        //生产者
        new Thread() {
            @Override
            public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object) {
                        object.notify();
                    }
                    System.out.println("2.包子好了");
                }
        }.start();
    }
}
