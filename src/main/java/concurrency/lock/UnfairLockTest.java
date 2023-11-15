package concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

public class UnfairLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            System.out.println("business code");
        }finally{
            lock.unlock();
        }
    }
}
