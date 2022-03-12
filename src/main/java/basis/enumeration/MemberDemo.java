package basis.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 利用枚举消除if/else的案例
 * 好像是 策略模式 又像 模版方法
 *
 * @Author qinwen
 * @Date 2022/3/10 5:43 下午
 */
public class MemberDemo {
    public static void main(String[] args) {
        User user = new User(1L, "bravo", Constants.GOLD_MEMBER);
        BigDecimal productPrice = new BigDecimal("1000");
        BigDecimal discountedPrice = calculateFinalPrice(productPrice, user.getMemberType());
        System.out.println(discountedPrice);

        User user2 = new User(1L, "bravo", MemberEnum.SILVER_MEMBER.getType());
        BigDecimal productPrice2 = new BigDecimal("1000");
        BigDecimal discountedPrice2 = MemberEnum.getMemberEnumByType(user2.getMemberType()).calculateFinalPrice(productPrice2);
        System.out.println(discountedPrice2);
    }

    /**
     * 根据会员身份返回折扣后的商品价格
     *
     * @param originPrice
     * @param type
     * @return
     */
    public static BigDecimal calculateFinalPrice(BigDecimal originPrice, Integer type) {
        if (Constants.GOLD_MEMBER.equals(type)) {
            return originPrice.multiply(new BigDecimal("0.6"));
        } else if (Constants.SILVER_MEMBER.equals(type)) {
            return originPrice.multiply(new BigDecimal("0.7"));
        } else if (Constants.BRONZE_MEMBER.equals(type)) {
            return originPrice.multiply(new BigDecimal("0.8"));
        } else {
            return originPrice;
        }
    }
}

@Data
@AllArgsConstructor
class User {
    private Long id;
    private String name;
    /**
     * 会员身份
     * 1：黄金会员，6折优惠
     * 2：白银会员，7折优惠
     * 3：青铜会员，8折优惠
     */
    private Integer memberType;
}

class Constants {
    /**
     * 黄金会员
     */
    public static final Integer GOLD_MEMBER = 1;
    /**
     * 白银会员
     */
    public static final Integer SILVER_MEMBER = 2;
    /**
     * 青铜会员
     */
    public static final Integer BRONZE_MEMBER = 3;
}

@Getter
enum MemberEnum {
    /**
     * 黄金会员
     */
    GOLD_MEMBER(1, "黄金会员") {
        @Override
        protected BigDecimal calculateFinalPrice(BigDecimal originPrice) {
            return originPrice.multiply(new BigDecimal("0.6"));
        }
    },
    /**
     * 白银会员
     */
    SILVER_MEMBER(2, "白银会员") {
        @Override
        protected BigDecimal calculateFinalPrice(BigDecimal originPrice) {
            return originPrice.multiply(new BigDecimal("0.7"));
        }
    },
    /**
     * 青铜会员
     */
    BRONZE_MEMBER(3, "青铜会员") {
        @Override
        protected BigDecimal calculateFinalPrice(BigDecimal originPrice) {
            return originPrice.multiply(new BigDecimal("0.8"));
        }
    },
    ;

    private final Integer type;
    private final String desc;

    MemberEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 定义抽象方法，留给子类实现
     *
     * @param originPrice 原价
     * @return BigDecimal
     */
    protected abstract BigDecimal calculateFinalPrice(BigDecimal originPrice);

    /**
     * foreach 写法 减少list.size()
     * @param type
     * @return
     */
    public static MemberEnum getMemberEnumByType(Integer type) {
        for (MemberEnum memberEnum : MemberEnum.values()) {
            if (memberEnum.getType().equals(type)) {
                return memberEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Enum type:" + type);
    }

    /**
     * jdk1.8写法
     * Stream.of() == Arrays.stream()
     *
     * @param type
     * @return
     */
    public static MemberEnum getMemberEnumByType2(Integer type) {
        Objects.requireNonNull(type);
        return Stream.of(values()).filter(value -> value.getType().equals(type)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Enum type:" + type));
    }

    private static final Map<Integer, MemberEnum> cache = new HashMap<>();

    static {
        for (MemberEnum value : values()) {
            cache.put(value.getType(), value);
        }
    }

    public static MemberEnum getMemberEnumByType3(Integer type) {
        return cache.getOrDefault(type, null);
    }
}
