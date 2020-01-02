package org.zxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "application.properties")
@ContextConfiguration(classes = RedisApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisTemplate.opsForValue().set("name", "haha");
    }

    /**
     * 插入缓存数据指定超时时间
     */
    @Test
    public void setTimeout() {
        redisTemplate.opsForValue().set("name-timeout", "haha",1, TimeUnit.MINUTES);
    }

    /**
     * 获取缓存数据
     */
    @Test
    public void get() {
       String object = redisTemplate.opsForValue().get("name-timeout");
        System.out.println(object);
    }

}
