package basis.annotations;

/**
 * @Author qinwen
 * @Date 2021/8/31 8:51 上午
 */
public @interface MyAnnotation {
    /**
     * 属性
     *
     *
     * @return
     */
    String getValue() default "no description"; // 默认值
}
