package concurrency.basic;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    @Test
    void testChangeNameThread(){
        Thread.currentThread().setName("Test Thread");

        for (int i=1; i<10 ;i++){
            System.out.println("for1: "+ Thread.currentThread().getName());
        }

        ThreadExample tt = new ThreadExample("i'm changnamethread.");
        tt.start();

        for (int i=1; i<10 ;i++){
            System.out.println("for2: "+ Thread.currentThread().getName());
        }
    }

    @Test
    void testBuyTicketThread(){
        BuyTicketThread window1= new BuyTicketThread("售票窗口1");
        BuyTicketThread window2= new BuyTicketThread("售票窗口2");
        BuyTicketThread window3= new BuyTicketThread("售票窗口3");

        window1.start();
        window2.start();
        window3.start();
    }

    @Test
    void testRunnableThread(){
        RunnableExample rt = new RunnableExample();
        Thread tt = new Thread(rt);
        tt.setName("i'm runnable thread.");
        tt.setDaemon(true);   //设置为伴随线程
        tt.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }
    }

    @Test
    void testBuyTicketRunnable(){
        BuyTicketRunnable br = new BuyTicketRunnable(); //三个线程共享同一个线程对象br

        Thread t1 = new Thread(br, "window1");
        t1.setPriority(10);  //这里只能让t1更有可能获得CPU调度
        t1.start();

        Thread t2 = new Thread(br,"window2");
        t2.setPriority(1);
        t2.start();

        Thread t3 = new Thread(br,"window3");
        t3.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main");
        }
    }

    @Test
    void testBuyTicketCallable() throws ExecutionException, InterruptedException {
        BuyTicketCallable bc = new BuyTicketCallable();
        FutureTask futureTask = new FutureTask(bc);

        Thread t= new Thread(futureTask);
        t.start();

        Object result = futureTask.get();
        System.out.println("result: "+result);
    }
}
