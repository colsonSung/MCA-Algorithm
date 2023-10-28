package designpattern.singleton;

/**
 * 静态内部类中创建单例
 * 由于静态内部类的特性，在装载内部类的时候才会创建单例对象，所以也是懒加载模式
 * 同时由于类的加载机制（JVM）决定了类加载肯定是线程安全的，所以这种方法同时也是线程安全的
 *
 * 故优先推荐使用这种方法来使用单例模式
 */
public class Singleton_LazyStaticInnerClass {
    private Singleton_LazyStaticInnerClass() {
        if(SingletonHandler.instance != null){ //避免反射机制对于单例模式的破坏
            throw new RuntimeException("不允许非法访问！");
        }
    }

    public static Singleton_LazyStaticInnerClass getInstance(){
        return SingletonHandler.instance;
    }

    private static class SingletonHandler{
        private static Singleton_LazyStaticInnerClass instance = new Singleton_LazyStaticInnerClass();
    }
}
