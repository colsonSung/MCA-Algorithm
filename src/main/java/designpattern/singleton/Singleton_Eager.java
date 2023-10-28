package designpattern.singleton;

/**
 * 饿汉式：在类加载期间初始化私有的静态实例，保证instance创建过程是线程安全的。
 * 优点：不支持延迟加载，获取实例对象速度比较快
 * 缺点：如果对象比较大，容易造成内存空间的浪费
 */
public class Singleton_Eager {

    //1. 私有构造方法
    private Singleton_Eager(){

    }

    //2. 创建私有静态的全局访问对象
    private static Singleton_Eager instance = new Singleton_Eager();

    //3. 提供全局访问点
    public static Singleton_Eager getInstance(){
        return instance;
    }
}
