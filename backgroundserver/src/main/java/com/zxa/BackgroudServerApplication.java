package com.zxa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BackgroudServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackgroudServerApplication.class,args);
    }
}
