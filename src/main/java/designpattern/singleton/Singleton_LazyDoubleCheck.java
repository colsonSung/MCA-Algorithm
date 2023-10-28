package designpattern.singleton;

import java.io.Serializable;

/**
 * 通过双重校验的方式来创建线程安全的单例
 */
public class Singleton_LazyDoubleCheck implements Serializable {
    private Singleton_LazyDoubleCheck(){

    }

    private volatile static Singleton_LazyDoubleCheck instance; //volatile关键字

    public static Singleton_LazyDoubleCheck getInstance() {
        if(instance == null){
            synchronized (Singleton_LazyDoubleCheck.class){
                if(instance == null){
                    /**
                     * new Singleton_LazyDoubleCheck() 在JVM中理论的步骤如下：
                     * 1. 在内存中分配存储空间
                     * 2. 初始化对象
                     * 3. 将instance引用指向所分配的存储空间
                     *
                     * 可能存在的问题：JVM可能会对上面的步骤进行指令重排序，实际的执行步骤可能是1->3->2,
                     * 假设当A线程获取到synchronized锁之后按照1->3->2重排序后的步骤执行到第3步时，锁被线程B获取了，进而继续执行程序，
                     * 线程B发现instance非null，故跳转到最后return instance，而线程A的第2步-初始化对象还未完成，则线程B return的就是个半成品。
                     *
                     * 解决方法：在instance声明处添加volatile 关键字，保证变量的可见性；
                     * 作用：
                     *      1. volatile关键字可能使得变量被某线程修改时，其他线程能够立即知道修改后的值；
                     *      2. volatile关键字可以屏蔽指令重排序
                     */
                    instance = new Singleton_LazyDoubleCheck();
                }
            }
        }
        return instance;
    }

    //此方法可以解决序列化对于单例的破坏
    private Object readResolve(){
        return instance;
    }
}
