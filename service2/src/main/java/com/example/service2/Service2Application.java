package com.example.service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// 开启nacos
@EnableDiscoveryClient
// 开启Feign
@EnableFeignClients
public class Service2Application {
    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

    // 启动负载均衡
    @LoadBalanced
    // 注册对象
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
