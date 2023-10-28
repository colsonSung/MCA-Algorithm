package designpattern.singleton;

/**
 * 枚举类 单例模式
 * 特点：基于枚举类自身的单例模式来实现
 *      能够阻止反射的破坏：在反射方法中，明确不允许通过反射创建枚举对象
 *      能够阻止序列化的破坏：序列化时仅将name输出到了结果中，反序列化时则是通过Enum的valueOf()方法，根据结果文件中name来查找对应的enum对象，所以得到的是同一个对象
 */
public enum Singleton_Enum {
    INSTANCE
    ;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Singleton_Enum getInstance(){
        return INSTANCE;
    }
}
