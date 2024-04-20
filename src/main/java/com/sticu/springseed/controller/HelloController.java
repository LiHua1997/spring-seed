package com.sticu.springseed.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Api(tags = "测试", description = "用户相关接口")
public class HelloController {
    @GetMapping
    @PreAuthorize("hasAuthority('test')")
    public String hello(){
        return "hello";
    }
}
