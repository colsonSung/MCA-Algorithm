package concurrency;

public class RunnableExample implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("thread name :"+ Thread.currentThread().getName()+ i);
        }
    }
}