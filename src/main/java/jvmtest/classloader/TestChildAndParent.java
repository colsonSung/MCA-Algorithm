package jvmtest.classloader;

public class TestChildAndParent {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(TestChildAndParent.class.getClassLoader());
        System.out.println(TestChildAndParent.class.getClassLoader().getClass().getClassLoader());
        System.out.println(TestChildAndParent.class.getClassLoader().getParent()); //EXT PlatformClassLoader
        System.out.println(TestChildAndParent.class.getClassLoader().getParent().getClass().getClassLoader());

        System.out.println("==================================");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("==================================");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("==================================");
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("Load Class Test");
        System.out.println(TestChildAndParent.class.getClassLoader().loadClass("jvmtest.classloader.ToBeLoadClass").getName());
    }
}
