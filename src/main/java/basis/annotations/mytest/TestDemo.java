package basis.annotations.mytest;

/**
 * @Author qinwen
 * @Date 2021/12/16 10:38 下午
 */
public class TestDemo {

    @MyBefore
    public void setUp() {
        System.out.println("before test...");
    }

    @MyTest
    public void testSave() {
        System.out.println("test save...");
    }

    @MyTest
    public void testDelete() {
        System.out.println("test delete...");
    }

    @MyAfter
    public void setDown() {
        System.out.println("after test...");
    }
}
