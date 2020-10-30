package com.zxb.admin.controller;

import com.zxb.admin.dto.menu.AddMenuDTO;
import com.zxb.admin.dto.menu.GetMenuInfoDTO;
import com.zxb.admin.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxb.web.annotation.ZxbLog;
import org.zxb.web.vo.Result;

import javax.validation.Valid;

/**
 * 菜单相关接口
 *
 * @author zjx
 * @date 2020/7/10 14:18
 */
@ZxbLog
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单相关接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/addMenu")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    public Result addMenu(@RequestBody @Valid AddMenuDTO dto) {
        return null;
    }

    @GetMapping("/getMenuInfo")
    @ApiOperation(value = "获取用户角色权限菜单", notes = "获取用户角色权限菜单")
    public Result<GetMenuInfoDTO> getMenuInfo() {
        return Result.buildSuccess(menuService.queryAll());
    }

}
