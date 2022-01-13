package basis.annotations.myjpa;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author qinwen
 * @Date 2021/12/23 8:28 上午
 */
@Data
@Table("t_jpa_user")
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
}
