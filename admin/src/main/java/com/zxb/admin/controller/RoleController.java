package com.zxb.admin.controller;

import com.zxb.admin.dto.role.AddRole;
import com.zxb.admin.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxb.web.vo.Result;

import javax.validation.Valid;

/**
 * 角色相关接口
 *
 * @author zjx
 * @date 2020/7/10 0010 14:18
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色相关接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public Result addRole(@RequestBody @Valid AddRole dto) {
        return null;
    }

}
