package com.zxa.consumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConsumersApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumersApplication.class,args);
    }
}
