package com.example.controller;

import com.example.common.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/test")
    @PreAuthorize("hasRole('admin')")
    public R test(){
        return R.succ("1111");
    }
}
