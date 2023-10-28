package concurrency.feature;

public class ThreadVolatileExam {
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(flag){
                //此处使用sout同样可以触发重新读取flag变量的操作，因为print方法内含了synchronized
                //System.out.println("t1 will end.");
            }
            System.out.println("t1 ended.");
        });
        t1.start();

        Thread.sleep(10);
        flag = false;
        System.out.println("main end.");
    }
}
