package basis.annotations.myjpa;

/**
 * @Author qinwen
 * @Date 2022/1/10 8:44 上午
 */
public class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User("qinwen", 24);
        userDao.add(user);
    }
}
