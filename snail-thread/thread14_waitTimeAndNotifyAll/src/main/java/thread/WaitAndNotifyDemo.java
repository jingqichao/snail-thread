package thread;

public class WaitAndNotifyDemo {

    public static void main(String[] args) {
        final Object object = new Object();
        //消费者1
        new Thread() {
            @Override
            public void run() {
                    synchronized (object) {
                        System.out.println("消费者1:老板，我要买包子");
                        try {
                            object.wait();
                            //object.wait(5000);毫秒值结束之后如果未被唤醒，会自动醒来，进入运行或者阻塞状态
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("消费者1:吃包子");
                    }
                }
        }.start();
        //消费者2
        new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("消费者2:老板，我要买包子");
                    try {
                        object.wait();
                        //object.wait(5000);毫秒值结束之后如果未被唤醒，会自动醒来，进入运行或者阻塞状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("消费者2:吃包子");
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
//                        object.notify();
                        object.notifyAll();
                        System.out.println("------------------");
                        System.out.println("老板：包子好了");
                        System.out.println("-----------------------");
                    }
                }
        }.start();
    }
}
