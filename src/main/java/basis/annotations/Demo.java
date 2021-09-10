package basis.annotations;

/**
 * @Author qinwen
 * @Date 2021/9/10 8:31 上午
 */
@MyAnnotation(getValue = "annotation on class")
public class Demo {

    @MyAnnotation(getValue = "annotation on field")
    public String name;

    @MyAnnotation(getValue = "annotation on method")
    public void hello() {
    }

    @MyAnnotation() // 故意不指定getValue
    public void defaultMethod() {

    }

}

