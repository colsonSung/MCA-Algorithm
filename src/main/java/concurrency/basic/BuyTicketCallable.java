package concurrency.basic;

import java.util.Random;
import java.util.concurrent.Callable;

public class BuyTicketCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);  //至少过1秒之后再继续下一次循环，人为进行阻塞
            result = new Random().nextInt();
            System.out.println("callable: "+result);
        }
        return result;
    }
}
