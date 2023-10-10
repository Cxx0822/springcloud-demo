package com.example.service2.controller;

import com.example.common.result.AxiosResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("service2")
public class Service2Controller {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("test")
    public AxiosResult test() {
        // 直接使用微服务名字， 从nacos中获取服务地址 (需要添加依赖并启动负载均衡)
        String url = "example-service1";

        return restTemplate.getForObject("http://" + url + "/service1/test", AxiosResult.class);
    }
}
