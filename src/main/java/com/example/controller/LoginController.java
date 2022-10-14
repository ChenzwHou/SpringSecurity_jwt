package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.R;
import com.example.entity.SysUser;

import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/login")
    public R login(@RequestBody SysUser sysUser){
        return sysUserService.login(sysUser);
    }







}
