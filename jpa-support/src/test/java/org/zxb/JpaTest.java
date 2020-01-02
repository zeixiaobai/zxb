package org.zxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "application.yml")
@ContextConfiguration(classes = JpaApplication.class)
public class JpaTest {

    @Value(value="${test}")
    private String test;

    @Test
    public void testBoot(){
        System.out.println(test);
        assert (true);
    }

}
