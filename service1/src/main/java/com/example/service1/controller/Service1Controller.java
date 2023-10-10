package com.example.service1.controller;

import com.example.common.result.AxiosResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class Service1Controller {

    @GetMapping("/test")
    public AxiosResult test() {
        return AxiosResult.ok().message("service1 test");
    }
}
