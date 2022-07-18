package improve.concurrent.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qinwen
 * @Date 2021/12/3 11:03 上午
 */
public class FriendTest {

    public static void main(String[] args) {
        GirlFriend girlFriend = new GirlFriend();
        // girlFriend.setFlag(true);
        // girlFriend.setName("新垣结衣");
        // girlFriend.setAge(18);

        List<String> list = new ArrayList<>();
        list.add("苹果");
        // girlFriend.setList(list);
        girlFriend.eat();
        System.out.println(ClassLayout.parseInstance(girlFriend).toPrintable());

        List<GirlFriend> girlFriendList = new ArrayList<>();
        girlFriendList.add(girlFriend);
        System.out.println(ClassLayout.parseInstance(girlFriendList).toPrintable());

        // 引用句柄, byte, boolean, char, short, int, float, double, long长度
        // System.out.println(VM.current().details());
    }
}
