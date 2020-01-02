package org.zxb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxb.web.annotation.ZxbLog;
import org.zxb.web.bean.User;
import org.zxb.web.exception.ValidateException;

/**
 * @description  BaseController
 * @author zjx
 * @date 2020/1/2
 */
@RestController
@ZxbLog
public class BTController extends BaseController{

    @GetMapping("test/get")
    public String get(@RequestParam(name="id2")  String id){
        System.out.println(id);
        return super.build(id);
    }

    @GetMapping("test/getUser")
    public String getUser(User user) throws ValidateException {
        validate(user);
        return super.build(user);
    }

}
