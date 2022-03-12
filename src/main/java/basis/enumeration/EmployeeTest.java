package basis.enumeration;

import lombok.Data;
import lombok.Getter;

/**
 * 山寨枚举
 * <p>
 * 需求：在Employee类中，定义一个字段，用来表示在哪一天休息（星期几）
 *
 * @Author qinwen
 * @Date 2022/3/9 3:25 下午
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee = new Employee();
        WeekDay[] values = WeekDay.values();
        values[0] = values[1];
        employee.setRestDay(WeekDay.MONDAY);
    }
}

@Data
class Employee {
    /**
     * 指定员工在哪一天休息
     */
    private WeekDay weekDay;

    public void setRestDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }
}

/**
 * 某种类型 他只有七个元素
 */
@Getter
class WeekDay {
    public static final WeekDay MONDAY;
    public static final WeekDay TUESDAY;
    public static final WeekDay WEDNESDAY;
    public static final WeekDay THURSDAY;
    public static final WeekDay FRIDAY;
    public static final WeekDay SATURDAY;
    public static final WeekDay SUNDAY;

    private static final WeekDay[] VALUES;

    static {
        // 之前说过，final字段赋值有三种形式，现在我们换成静态代码块赋值
        MONDAY = new WeekDay(1, "星期一");
        TUESDAY = new WeekDay(2, "星期二");
        WEDNESDAY = new WeekDay(3, "星期三");
        THURSDAY = new WeekDay(4, "星期四");
        FRIDAY = new WeekDay(5, "星期五");
        SATURDAY = new WeekDay(6, "星期六");
        SUNDAY = new WeekDay(7, "星期日");

        //
        VALUES = new WeekDay[]{
                MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        };
    }

    /**
     * final修饰不变量 只能被赋值一次
     */

    private final Integer code;
    private final String desc;


    /**
     * 私有构造
     */
    private WeekDay(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WeekDay[] values() {
        return VALUES;
    }


}



