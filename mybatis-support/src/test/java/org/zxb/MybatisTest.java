package org.zxb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        user.setId(4);
        user.setUsername("wanwu");
        user.setPassword("123456");
        user.setAddress("龙岗");
        user.setAge(28);
        userMapper.insert(user);
    }

    @Test
    public void select() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void selectList() {

        Page page = new Page();
        page.setPageNum(0);
        page.setEverypageNum(2);
        myInterceptor.getThreadLocal().set(page);

        List<User> list = userMapper.selectAll();
        Assert.assertEquals(2, list.size());
    }
}
