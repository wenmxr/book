package basis.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Author qinwen
 * @Date 2022/4/2 5:42 下午
 */
public class NewDateApiTest {
    public static void main(String[] args) {
        // test01();
        // test02();
        // test03();
        // test04();
        // test05();
        test06();

    }

    /**
     * 创建时间
     */
    public static void test02() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());

        // 计算前一天同一时刻
        System.out.println("计算前一天同一时刻");
        LocalDateTime localDateTime1 = localDateTime.plus(-1, ChronoUnit.DAYS);
        System.out.println("Today: " + localDateTime);
        System.out.println("Yesterday" + localDateTime1);

        // 计算当天最小时间 最大时间
        System.out.println("计算当天最小时间 最大时间");
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));

        System.out.println("计算一周、一个月、一年前的当前时刻：");
        System.out.println(localDateTime.minus(1, ChronoUnit.WEEKS));
        System.out.println(localDateTime.minus(1, ChronoUnit.MONTHS));
        System.out.println(localDateTime.minus(1, ChronoUnit.YEARS));
    }

    /**
     * 增减时间
     */
    public static void test01() {
        // 老api 可以new
        Date date = new Date();
        System.out.println("Old api: " + date);

        // 新api 不需要new 直接工厂给
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // 组合
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime1);

        // 指定
        LocalDate localDate1 = LocalDate.of(2022, 4, 2);
        LocalTime localTime1 = LocalTime.of(18, 0, 3);
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 2, 22, 2, 2, 2);
        System.out.println(localDate1);
        System.out.println(localTime1);
        System.out.println(localDateTime2);
    }

    /**
     * 修改时间
     */
    public static void test03() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.with(ChronoField.DAY_OF_MONTH, 1);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(2);
        System.out.println(localDateTime2);

    }

    /**
     * 比较时间
     */
    public static void test04() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusMinutes(1);

        System.out.println(localDateTime1.isAfter(localDateTime));
        System.out.println(localDateTime1.isBefore(localDateTime));
        System.out.println(localDateTime1.isEqual(localDateTime));
    }

    /**
     * LocalDateTime与Date互转
     * 媒介是Instant（格林尼治时间）
     * https://www.cnblogs.com/woshimrf/p/LocalDateTime-to-Date.html
     */
    public static void test05() {
        System.out.println("============LocalDateTime 2 Date");
        // 先把LocalDateTime变为ZonedDateTime，然后调用toInstant()
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        // Instant是代表本初子午线的时间，所以比我们的东八区要晚8小时
        Instant instant = zonedDateTime.toInstant();
        System.out.println(zonedDateTime);
        System.out.println(instant);

        Date date = Date.from(instant);
        System.out.println(date);

        System.out.println("===========Date 2 LocalDateTime");

        // Date也有toInstant()
        Date date1 = new Date();
        Instant instant1 = date1.toInstant();
        System.out.println(date1);
        System.out.println(instant1);

        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        System.out.println(localDateTime1);

        System.out.println("============2 Second");

        System.out.println(date1.getTime());
        System.out.println(date1.getTime() / 1000);

        long l = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(l);

        LocalDateTime localDateTime2 = LocalDateTime.ofEpochSecond(l, 0, ZoneOffset.ofHours(8));
        System.out.println(localDateTime2);


    }

    /**
     * 格式化
     */
    public static void test06() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("格式化前：" + now);
        System.out.println("默认格式：" + now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("其他格式：" + now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("自定义格式：" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
