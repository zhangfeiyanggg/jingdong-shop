package com.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName RunXXXApplication
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/27
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jd.shop.mapper")
public class RunXXXApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunXXXApplication.class);
    }
}
