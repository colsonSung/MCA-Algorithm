package designpattern.singleton;

/**
 * 线程安全的
 * 通过synchronized关键字保证线程安全，防止多个线程同时调用
 * 缺点： synchronized 会将整个getInstance()方法锁住，并发度变的很低，易造成性能瓶颈
 */
public class Singleton_LazyThreadSafe {
    private Singleton_LazyThreadSafe(){

    }

    private static Singleton_LazyThreadSafe instance;

    public static synchronized Singleton_LazyThreadSafe getInstance() {
        if(instance == null){
            instance = new Singleton_LazyThreadSafe();
        }
        return instance;
    }
}
