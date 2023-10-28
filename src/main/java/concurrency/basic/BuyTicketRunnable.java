package concurrency.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuyTicketRunnable implements Runnable{
    //此处无需static
    int ticketNum = 10;

    Lock lock = new ReentrantLock(); //需要显式的进行上锁和关锁

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) { //100 people want to buy tickets
            lock.lock();
            try{
                if (ticketNum >0) {
                    System.out.println("get ticket "+ ticketNum-- + " from "+Thread.currentThread().getName());
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
