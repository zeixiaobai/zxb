
package org.zxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zxb.dao.UserDao;
import org.zxb.entity.User;
import org.zxb.plun.ExamplePlugin;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "application.yml")
@ContextConfiguration(classes = MybatisApplication.class)
public class MybatisTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ExamplePlugin myInterceptor;

    @Autowired
    private UserDao userDao;

    @Test
    public void boot() {
        jdbcTemplate.execute("select 1");
        assert (true);
    }

    @Test
    public void insert() {
        List<User> likeByName = userDao.findLikeByName("z");
        System.out.println(likeByName);
    }


    @Test
    public void select() {
        User user = new User();
        user.setId(1);
        User user2 = userDao.findById(1);
        System.out.println(user2.getId() + " " + user2.getUserName());

        User user3 = userDao.selectByPrimaryKey(1);
        System.out.println(user3.getId() + " " + user3.getUserName());
    }
}
