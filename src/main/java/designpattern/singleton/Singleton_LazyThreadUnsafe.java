package designpattern.singleton;

/**
 * 非线程安全的
 */
public class Singleton_LazyThreadUnsafe {
    private Singleton_LazyThreadUnsafe(){

    }

    private static Singleton_LazyThreadUnsafe instance;

    public static Singleton_LazyThreadUnsafe getInstance(){
        if(instance == null){
            instance = new Singleton_LazyThreadUnsafe();
        }
        return instance;
    }
}
