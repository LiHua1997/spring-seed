package com.sticu.springseed.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Api(tags = "测试", description = "用户相关接口")
public class HelloController {
    @GetMapping
    public String hello(){
        return "hello";
    }
}
