package concurrency.lock;

import org.openjdk.jol.info.ClassLayout;

public class SynchronizedPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                //t1 - 偏向锁
                System.out.println("t1: " + ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        synchronized (o){
            //main - 偏向锁--轻量级锁CAS -- 重量级锁
            System.out.println("main: "+ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
