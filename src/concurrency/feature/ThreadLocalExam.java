package concurrency.feature;

public class ThreadLocalExam {
    static ThreadLocal tl1 = new ThreadLocal();
    static ThreadLocal tl2 = new ThreadLocal();

    public static void main(String[] args) {
        tl1.set("123");
        tl2.set("456");

        Thread t1 = new Thread(()->{
            System.out.println("t1: "+tl1.get()); //无法获取到主线程中设置的变量值
            System.out.println("t1: "+tl2.get());
        });
        t1.start();

        System.out.println("main: "+tl1.get());
        System.out.println("main: "+tl2.get());
    }
}
