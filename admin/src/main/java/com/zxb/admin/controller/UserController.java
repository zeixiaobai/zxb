package com.zxb.admin.controller;

import com.zxb.admin.dto.user.LoginDTO;
import com.zxb.admin.service.UserService;
import com.zxb.admin.vo.user.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxb.web.annotation.ZxbLog;
import org.zxb.web.vo.Result;

import javax.validation.Valid;

/**
 * 用户管理相关接口
 *
 * @author zjx
 * @date 2020-07-10 13:29:20
 */
@ZxbLog
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public Result<LoginVO> login(@RequestBody @Valid LoginDTO dto) {
        return Result.buildSuccess(userService.login(dto));
    }


}
