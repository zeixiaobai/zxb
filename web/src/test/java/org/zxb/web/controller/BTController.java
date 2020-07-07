package org.zxb.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxb.common.dto.Result;
import org.zxb.web.annotation.ZxbLog;
import org.zxb.web.bean.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zjx
 * @description BaseController
 * @date 2020/1/2
 */
@RestController
@ZxbLog
@RequestMapping("/test")
@Validated
public class BTController  {

    @GetMapping("get")
    public Result get(@NotNull(message = "id不能为空") String id) {
        System.out.println(id);
        return Result.buildSuccess(id);
    }

    @GetMapping("getUser")
    public Result getUser(@Valid User user) {
        return Result.buildSuccess(user);
    }

}
