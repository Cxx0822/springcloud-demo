# SpringCloudAlibaba简介与项目创建

# SpringCloudAlibaba简介
&emsp;&emsp;微服务架构，就是将单体应用进一步拆分，拆分成更小的服务，每个服务都是一个可以独立运行的项目。     

&emsp;&emsp;[Spring Cloud](https://www.springcloud.cc/)是一系列框架的集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。      

&emsp;&emsp;[Spring Cloud Alibaba](https://sca.aliyun.com/zh-cn/) 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。    

# 项目环境
&emsp;&emsp;Java 11     
&emsp;&emsp;SpringBoot 2.7.16    
&emsp;&emsp;SpringCloud 2021.0.5    
&emsp;&emsp;SpringCloudAlibaba 2021.0.5.0  
&emsp;&emsp;IDEA 2023.1      

# 项目搭建
## 创建父工程
1. 打开IDEA，创建新工程，删除src目录和pom.xml中的parent部分。       
2. 在pom.xml文件中添加标签:

```xml
    <packaging>pom</packaging>
```
&emsp;&emsp;父工程只作为项目子模块的整合，不会生成jar包文件，因此需要将packing标签设置为pom。         

3. 指定springboot springCloud springCloudAlibaba 的版本，版本说明可以参考: https://github.com/alibaba/spring-cloud-alibaba/wiki

```xml
<properties>
    <java.version>11</java.version>
    <spring-boot.version>2.7.16</spring-boot.version>
    <spring-cloud.version>2021.0.5</spring-cloud.version>
    <spring-cloud-alibaba.version>2021.0.5.0</spring-cloud-alibaba.version>
</properties>
```

4. 声明springboot springCloud springCloudAlibaba 依赖

```xml
<!-- dependencyManagement:只声明依赖 并不实现引入 -->
<!-- 只有子项目中声明了该依赖项 且没有指定具体版本 子项目才会引入该依赖 -->
<!-- 如果子项目指定了具体版本 则会引入子项目的依赖 -->
<dependencyManagement>
    <!-- 4.添加springboot springCloud springCloudAlibaba的依赖 -->
    <dependencies>
        <!-- springCloud -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <!-- 指定scope为import 引入其依赖的版本 -->
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!-- SpringBoot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!-- SpringCloud alibaba -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${spring-cloud-alibaba.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

5. 添加其他公共依赖
```xml
<!-- 版本管理 -->
<properties>
    <swagger.version>3.0.0</swagger.version>
    <lombook.version>1.18.26</lombook.version>
    <hutool.version>5.8.22</hutool.version>
</properties>

<!-- dependencies:子项目会继承父项目的依赖 -->
<dependencies>
    <!-- swagger ui 3.0-->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>${swagger.version}</version>
    </dependency>

    <!--  lombok   -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombook.version}</version>
    </dependency>


    <!-- hu tool工具类 -->
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>${hutool.version}</version>
    </dependency>
</dependencies>
```

## 创建子工程
### 创建Common工程
1. 选择File->New->Module创建子工程。   
2. 创建完成后,会自动在父工程的pom文件中生成<modules>标签。   
3. 选择common工程的src->main->java包，添加相应的业务功能。    

### 创建其他子工程
1. 依次创建service1和service2子工程   
2. 在maven中添加依赖:  
```xml
<dependencies>
    <!--springboot 启动-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!--springboot 测试-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--springboot web-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

3. 在src->main->java包中创建controller包和ServiceApplication启动类。    
4. 配置application.yml文件:
```yml
server:
  port: 8081

spring:
  mvc:
    pathmatch:
      # 解决SpringBoot高版本(2.6以上) swagger-ui报错的问题
      matching-strategy: ANT_PATH_MATCHER
```

&emsp;&emsp;注意端口号的设置.   

5. 调用Common工程定义的类, 在工程中添加Common的依赖:
```xml
<dependencies>
    <!-- 引用common模块 -->
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>common</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

&emsp;&emsp;依次启动Service1和Service2工程，没有报错则配置成功。         

# 服务治理Nacos
&emsp;&emsp;[Nacos](https://nacos.io/zh-cn/index.html)是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。是Spring Cloud Alibaba 组件之一，负责服务注册发现和服务配置。       

## 安装Nacos
1. 在[官网](https://github.com/alibaba/nacos/releases)中下载压缩包并解压到相应的文件夹。      
2. 启动Nacos 
```cmd
cd nacos/bin
bash bin/startup.sh -m standalone 
```  

3. 访问本地Nacos控制台:http://127.0.0.1:8848/nacos/

## 注册服务到Nacos
1. 在服务的Maven中添加依赖:
```xml
<dependencies>
    <!-- Spring Cloud Alibaba Nacos discovery -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>

    <!-- 负载均衡 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>
</dependencies>
```

2. 在启动类中添加注解
```java
@SpringBootApplication
// 开启nacos
@EnableDiscoveryClient
public class Service1Application {
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }
}
```

3. 在appliction.yml中设置服务名和nacos服务地址
```yml
spring:
  # 配置nacos
  application:
    name: example-service1
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

4. 启动服务，查看Nacos控制台的服务器管理->服务列表中是否包含该服务。 
5. 启动多个服务，查看Nacos控制台的服务列表。   

## 服务间调用
&emsp;&emsp;在SpringCloud提供了两种方式来解决服务与服务通信的问题，RestTemplate和Feign。虽然从名字上看这两种调用的方式不同，但在底层还是和HttpClient一样，采用Http的方式进行调用的。只不过是对HttpClient进行的封装。      
### RestTemplate
&emsp;&emsp;例如在Service2中调用Service1的接口。  
1. 在Service2的启动类中声明RestTemplate。 
```java
// 启动负载均衡
@LoadBalanced
// 注册对象
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

2. 在Service2Controller的接口中调用Service1服务：
```java
@RestController
@RequestMapping("service2")
public class Service2Controller {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("testRestTemplate")
    public AxiosResult testRestTemplate() {
        // 直接使用微服务名字， 从nacos中获取服务地址 (需要添加依赖并启动负载均衡)
        String url = "example-service1";

        return restTemplate.getForObject("http://" + url + "/service1/test", AxiosResult.class);
    }
}
```

3. 启动2个服务，在浏览器中输入http://127.0.0.1:8082/service2/testRestTemplate ，查看是否正确输出Service1的内容。     

### Feign
&emsp;&emsp;Feign是Spring Cloud提供的一个声明式的伪Http客户端，它使得调用远程服务就像调用本地服务一样简单，只需要创建一个接口并添加一个注解即可。     
&emsp;&emsp;Nacos很好的兼容了Feign，Feign默认集成了Ribbon，所以在Nacos下使用Fegin默认就实现了负载均衡的效果。  
&emsp;&emsp;例如在Service2中调用Service1的接口。  
1. 在Service2中添加依赖:
```xml
<!-- Feign组件 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```     

2. 在启动类中添加注解
```java
@SpringBootApplication
// 开启nacos
@EnableDiscoveryClient
// 开启Feign
@EnableFeignClients
public class Service2Application {
    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }
}
```

3. 定义与Service1服务的调用接口
```java
/**
 * 调用example-service1服务的/service1/test接口
 * example-service1为注册中心的服务名
 */
@FeignClient(name = "example-service1")
public interface Service1Feign {
    @RequestMapping("/service1/test")
    AxiosResult test();
}
```

&emsp;&emsp;example-service1为注册中心的服务名，/service1/test是Service1中controller中的接口。      

4. 在controller中调用接口实现远程访问
```java
@RestController
@RequestMapping("service2")
public class Service2Controller {
    @Resource
    private Service1Feign service1Feign;

    @GetMapping("testFeign")
    public AxiosResult testFeign() {
        return service1Feign.test();
    }
}
```

5. 启动2个服务，在浏览器中输入http://127.0.0.1:8082/service2/testFeign ，查看是否正确输出Service1的内容。    

# 服务网关 Gateway
&emsp;&emsp;所谓的网关，就是指系统的统一入口，它封装了应用程序的内部结构，为客户端提供统一服务，一些与业务本身功能无关的公共逻辑可以在这里实现，诸如认证、鉴权、监控、路由转发等等。(类似于Web前端中路由的概念)          
## 创建gateway模块
1. 添加依赖:  
```xml
<dependencies>
    <!--gateway网关-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>

    <!--nacos客户端-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    </dependencies>
```

2. 添加启动类
```java
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

3. 添加配置文件
```yml
server:
  port: 8080

spring:
  profiles:
    active: dev
  application:
    name: gateway
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
    gateway:
      discovery:
        locator:
          # 让gateway可以发现nacos中的微服务
          enabled: true

      routes:
        # 路由id
        - id: "example-service1"
          # 真实url
          uri: http://127.0.0.1:8081
          # 断言 路径名
          predicates:
            - Path=/service1/**

        # 路由id
        - id: "example-service2"
          # 真实url
          uri: http://127.0.0.1:8082
          # 断言 路径名
          predicates:
            - Path=/service2/**
```

4. 利用网关调用微服务    
&emsp;&emsp;启动网关服务、Service1和Service2服务，在浏览器中输入http://127.0.0.1:8080/service1/test ，即可调用Service1的service1/test接口。     


# 服务部署
