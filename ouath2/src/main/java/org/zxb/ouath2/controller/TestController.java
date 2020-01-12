package org.zxb.ouath2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author: zjx
 * @time: 2020/1/12 10:15
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String test() {
        System.out.println("hello");
        return "ok";
    }

}
