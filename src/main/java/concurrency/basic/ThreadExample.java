package concurrency.basic;

public class ThreadExample extends Thread{

    public ThreadExample(String name){
        super(name);
    }

    @Override
    public void run() {
//        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println("ThreadExample Name: "+ this.getName());
        }
    }
}
