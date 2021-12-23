package basis.reflect.myjpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author qinwen
 * @Date 2021/12/23 8:35 上午
 */
public class JpaTest {
    public static void main(String[] args) {
        new B();
    }
}
class A<T> {
    public A() {
        // 获取子类的Class对象
        Class<? extends A> subClass = this.getClass();
        System.out.println(subClass);
        // 子类Class获取父类的class对象
        Class<?> superclass = subClass.getSuperclass();
        System.out.println(superclass);
        // 子类Class获取范型父类的Class对象，相当于拿到了整个A<String>
        Type genericSuperclass = subClass.getGenericSuperclass();
        System.out.println(genericSuperclass);
        System.out.println(genericSuperclass.getClass());

        // 本质是ParameterizedTypeImpl 可向下强转
        ParameterizedType genSuperclass = (ParameterizedType) genericSuperclass;

    }
}

class B extends A<String> {

}

class C extends A<Integer> {

}
