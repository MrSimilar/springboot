package com.xinyet.mybatis.controller;

import com.xinyet.mybatis.entity.OsUser;
import com.xinyet.mybatis.service.IOsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author via
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/os-user")
public class OsUserController {
    @Autowired
    private IOsUserService userService;

    @RequestMapping("/getUserList")
    public List<OsUser> getUserList() {
        return userService.getUserList();
    }

    @RequestMapping("addUser")
    public Integer addUser(@RequestBody OsUser user) {
        return userService.addUser(user);
    }
}
