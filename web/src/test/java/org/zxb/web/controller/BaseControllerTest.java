package org.zxb.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.zxb.common.utils.JSONUtil;
import org.zxb.web.bean.User;
import org.zxb.web.vo.Result;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(value = {"application.properties"})
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        String response = this.mvc.perform(get("/test/get?id=123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Result<String> result = JSONUtil.parseObject(response, new TypeReference<Result<String>>() {
        });
        Assert.assertEquals("123",result.getData());
    }

    @Test
    public void testGetUser() throws Exception {
        String response = this.mvc.perform(get("/test/getUser?name=zhangsan&time=11").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Result<User> result = JSONUtil.parseObject(response, new TypeReference<Result<User>>() {
        });
        Assert.assertEquals("zhangsan",result.getData().getName());
    }
}
