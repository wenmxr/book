package basis.optional;


import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

/**
 * 空指针包装工具类
 *
 * @Author qinwen
 * @Date 2022/4/7 5:55 下午
 */
public class MiniNullWrapper<T> {

    /**
     * 实际值
     */
    private final T value;

    /**
     * 无参构造 默认包装null
     */
    public MiniNullWrapper() {
        this.value = null;
    }

    /**
     * 有参构造 包装传入的value
     *
     * @param value
     */
    public MiniNullWrapper(T value) {
        this.value = value;
    }

    /**
     * 静态方法 返回了一个包装了null的Wrapper
     *
     * @param <T>
     * @return
     */
    public static <T> MiniNullWrapper<T> empty() {
        // 调用无参构造，返回包装了null的Wrapper
        System.out.println("由于value为null，直接返回包装了null的Wrapper，让流程继续往下");
        return new MiniNullWrapper<>();
    }

    /**
     * 静态方法 返回了一个包装value的Wrapper （value可能为null）
     *
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> MiniNullWrapper<T> ofNullable(T value) {
        return new MiniNullWrapper<>(value);
    }

    /**
     * 核心方法
     * 1、如果value为null，直接返回空的Wrapper
     * 2、如果value不为null，则使用mapper对value进行处理，往下剥一层（这是关键，一有机会就要往下剥一层，否则就是原地踏步）
     *
     * @param mapper
     * @param <U>
     * @return
     */
    public <U> MiniNullWrapper<U> map(Function<T, U> mapper) {
        Objects.requireNonNull(mapper);
        if (value == null) {
            return MiniNullWrapper.empty();
        } else {
            return MiniNullWrapper.ofNullable(mapper.apply(value));
        }
    }

    /**
     * 终端操作
     *
     * @param other
     * @return
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    public static void main(String[] args) {
        Son son = new Son("大头");
        Father father = new Father();
        father.setSon(son);
        GrandPa grandPa = new GrandPa();
        grandPa.setFather(father);
        // 处理grandPa，观察map()中的处理方法有没有被调用
        String s = MiniNullWrapper.ofNullable(grandPa).map(GrandPa::getFather).map(Father::getSon).map(Son::getName).orElse("没找到儿子的名字");
        System.out.println(s);

        String s1 = MiniNullWrapper.ofNullable(new GrandPa()).map(GrandPa::getFather).map(Father::getSon).map(Son::getName).orElse("没找到儿子的名字");
        System.out.println(s1);


    }

    // ---- 没啥实质内容，就是几个简单的类，我在getter方法中打印了一些信息 ----
    static class GrandPa {
        private Father father;

        public Father getFather() {
            System.out.println("GrandPa#getFather被调用了");
            return father;
        }

        public void setFather(Father father) {
            this.father = father;
        }
    }

    static class Father {
        private Son son;

        public Son getSon() {
            System.out.println("Father#getSon被调用了");
            return son;
        }

        public void setSon(Son son) {
            this.son = son;
        }
    }

    @AllArgsConstructor
    static class Son {
        private String name;

        public String getName() {
            System.out.println("Son#getName被调用了");
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

