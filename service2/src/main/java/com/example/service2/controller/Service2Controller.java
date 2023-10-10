package com.example.service2.controller;

import com.example.common.result.AxiosResult;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("service2")
public class Service2Controller {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("test")
    public AxiosResult test() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("example-service1").get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();

        AxiosResult axiosResult = restTemplate.getForObject("http://" + url + "/service1/test", AxiosResult.class);
        return axiosResult;
    }
}
