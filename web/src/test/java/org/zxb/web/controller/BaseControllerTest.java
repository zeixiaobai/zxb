package org.zxb.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    }

    @Test
    public void testGetUser() throws Exception {
        String response = this.mvc.perform(get("/test/getUser?name=zhangsan&time=11").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }
}
