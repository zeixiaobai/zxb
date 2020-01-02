package org.zxb.web.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.zxb.web.bean.User;
import org.zxb.web.dto.Result;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(BTController.class)
@SpringBootTest(value={"application.properties"})
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        org.zxb.web.dto.Result result = new Result(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase());
        result.setData("123");
        this.mvc.perform(get("/test/get?id2=123").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string(JSON.toJSONString(result)));
    }

    @Test
    public void testGetUser() throws Exception {

        User user = new User();
        user.setName("zhangsan");

        org.zxb.web.dto.Result result = new Result(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase());
        result.setMessage("不能为空");
      //  result.setData(user);

        this.mvc.perform(get("/test/getUser?name=zhangsan").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string(JSON.toJSONString(result)));
    }
}
