package concurrency.basic;

public class BuyTicketThread extends Thread{
    public BuyTicketThread(String name){
        super(name);
    }

    static int ticketNum = 10;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (BuyTicketThread.class){  //使用同步代码块保证线程安全
                if(ticketNum >0){
                    System.out.println("get ticket: "+ ticketNum-- + " from "+this.getName());
                }
            }
        }
        super.run();
    }

    /**
     * 同步方法
     * 默认锁住的是当前对象this
     */
    private synchronized void buyTicket(){
        synchronized (BuyTicketThread.class){  //使用同步代码块保证线程安全
            if(ticketNum >0){
                System.out.println("get ticket: "+ ticketNum-- + " from "+this.getName());
            }
        }
    }
}
