package javasebasic.reflection;

import javasebasic.annotation.MyAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        System.out.println("Hello World!");

        Class o1 = MyAnnotation.class;  //获取注解的字节码对象
        System.out.println(o1.getName());
        System.out.println(o1.getDeclaredMethod("value"));  //获取注解的方法



        //获取 java.lang.Class 字节码对象，实际上只是等同于获取到了classpath路径指定的“类“（非类的实例）
        Class clazz = Class.forName("javasebasic.reflection.Pay");
        System.out.println("字节码对象名称: "+clazz.getName());
        Field f1 = clazz.getDeclaredField("channel");
        f1.setAccessible(true); //设置私有属性可以访问


        System.out.println("属性所在的类: "+ f1.getDeclaringClass());
        System.out.println("属性的名字:" + f1.getName());
        System.out.println("属性的类型: "+f1.getType());
        System.out.println("属性的修饰符: "+f1.getModifiers()); //private 2


        Method m = clazz.getMethod("pay", int.class);
        System.out.println("获取方法上的注解: "+ Arrays.toString(m.getDeclaredAnnotations()));  //获取方法上的运行时注解
        System.out.println("获取方法上的注解值: "+m.getDeclaredAnnotation(MyAnnotation.class).value());

        //创建类的实例对象
        Object o = clazz.getDeclaredConstructor().newInstance(); //注意 getDeclaredConstructors() 可以获取所有构造器,包括 private 的
        m.invoke(o, 518);


        Class a1 = new int[]{1,2,4, 6}.getClass();
        Class a2 = new String[]{"a","b","c"}.getClass();
        System.out.println(a1 == a2);

        Class a3 = new int[]{6,27,4}.getClass();
        System.out.println(a1 == a3);  //true 相同元素类型和维度的数组，得到的字节码对象是同一个


        Class i1 = int.class;  //基本数据类型的字节码对象
        System.out.println(i1.getName());


        Class v1 = void.class;  //void 关键字的字节码对象
        System.out.println(v1.getName());
    }
}
