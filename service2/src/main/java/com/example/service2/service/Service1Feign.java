package com.example.service2.service;

import com.example.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调用example-service1服务的/service1/test接口
 * example-service1为注册中心的服务名
 */
@FeignClient(name = "example-service1")
public interface Service1Feign {
    @RequestMapping("/service1/test")
    CommonResult test();
}
