package user.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.CountingDaoFactory;
import user.dao.CoutingConnectionMaker;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println("user.getId() + " + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");

        CoutingConnectionMaker ccm = context.getBean("connectionMaker", CoutingConnectionMaker.class);
        System.out.println("ccm.getCounter() = " + ccm.getCounter());
    }
}
