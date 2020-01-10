package org.zxb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zxb.dao.UserMapper;
import org.zxb.entity.User;
import org.zxb.plun.ExamplePlugin;
import org.zxb.plun.Page;

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
    private UserMapper userMapper;

    @Test
    public void boot() {
        jdbcTemplate.execute("select 1");
        assert (true);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setId(7);
        user.setUsername("zhaoqi");
        user.setPassword("123456");
        user.setAddress("南山");
        user.setAge(30);
        userMapper.insert(user);
    }

    @Test
    public void delete() {
        User user = new User();
        user.setUsername("zhaoqi");
        int i = userMapper.delete(user);
        Assert.assertTrue(i == 1);
    }

    @Test
    public void update() {
        User user = new User();
        user.setUsername("wanwu666");
        User whe = new User();
        whe.setId(6);
        int i = userMapper.update(user, whe);
        Assert.assertTrue(i == 1);
    }

    @Test
    public void select() {
        List<User> list = userMapper.select(null);
        for (User user2 : list) {
            System.out.println(user2.getId() + " " + user2.getUsername());
        }
        Assert.assertEquals("zhangsan", list.get(0).getUsername());

        User user = new User();
        user.setId(1);
        list = userMapper.select(user);
        for (User user2 : list) {
            System.out.println(user2.getId() + " " + user2.getUsername());
        }
    }
}
