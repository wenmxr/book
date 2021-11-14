package basis.annotations;

import java.lang.reflect.Field;

/**
 * 注解
 *
 * @Author qinwen
 * @Date 2021/9/14 8:27 上午
 */
public class AnnotationTest {
    public static void main(String[] args) throws NoSuchFieldException {
        // 获取类上的注解
        // Annotation 'MyAnnotation.class' is not retained for reflective access
        Class<Demo> clazz = Demo.class;
        MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnClass.getValue());

        // 获取成员变量上的注解
        Field field = clazz.getField("name");
        MyAnnotation annotationOnField = field.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnField);
    }
}
