package com.example.service1.controller;

import com.example.common.result.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class Service1Controller {

    @GetMapping("/test")
    public CommonResult test() {
        return CommonResult.ok().message("service1 test");
    }
}
