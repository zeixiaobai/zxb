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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "application.yml")
@ContextConfiguration(classes = MybatisApplication.class)
public class MybatisTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value(value="${test}")
    private String test;

    @Test
    public void boot(){
        Assert.assertEquals("111",test);
        jdbcTemplate.execute("select 1");
        assert(true);
    }

}
