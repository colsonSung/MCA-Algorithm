package designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;

class SingletonTest {

    @Test
    void getInstance() {
        Singleton_Eager instance1 = Singleton_Eager.getInstance();
        Singleton_Eager instance2 = Singleton_Eager.getInstance();

        assert instance1 == instance2;
    }

    @Test
    void getInstance02() {
        for(int i=0; i<=100 ;i++){
            new Thread(() -> {
                Singleton_LazyThreadUnsafe instance = Singleton_LazyThreadUnsafe.getInstance();
                System.out.println("instance id: "+ instance);
            }).start();
        }
    }

    /**
     * 反射机制可以强行获取类的私有变量和方法，进而破坏单例模式
     *
     * 解决办法：在InnerClassSingleton05_ThreadSafe的私有构造器方法中添加实例检查
     */
    @Test
    void test_reflection() throws Exception {
        Class<Singleton_LazyStaticInnerClass> clazz = Singleton_LazyStaticInnerClass.class;
        Constructor<Singleton_LazyStaticInnerClass> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true); //通过强制设置其访问权限为true

        Singleton_LazyStaticInnerClass illegalInstance =  declaredConstructor.newInstance();
        System.out.println("illegalInstance: " +illegalInstance);
    }

    /**
     * 通常情况下序列化也能破坏单例模式
     * 原因：ois.readObject()会通过反射调用对象的无参构造来创建新的对象实例 obj = desc.isInstantiable() ? desc.newInstance() : null;
     * 解决办法：
     *      如果实现了序列化接口的类中包含了readResolve()方法，就会通过反射的方式调用类的readResolve()方法
     *      故只要在单例类中添加readResolve()方法返回instance即可
     */
    @Test
    void test_serializable() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmpFile.obj"));
        oos.writeObject(Singleton_LazyDoubleCheck.getInstance());

        File tmpFile = new File("tmpFile.obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmpFile));
        Singleton_LazyDoubleCheck singleton = (Singleton_LazyDoubleCheck) ois.readObject();  //原因就在于ois.readObject()

        System.out.println(singleton);
        System.out.println(Singleton_LazyDoubleCheck.getInstance());
        System.out.println(singleton == Singleton_LazyDoubleCheck.getInstance());
    }
}