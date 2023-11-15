package javasebasic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, //类、接口或枚举
        ElementType.FIELD, //字段、枚举的常量
        ElementType.METHOD, //方法
        ElementType.PARAMETER, //方法参数
        ElementType.CONSTRUCTOR, //构造函数
        ElementType.LOCAL_VARIABLE, //局部变量
        ElementType.ANNOTATION_TYPE, //注解
        ElementType.PACKAGE //包
        })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "hello";
}
